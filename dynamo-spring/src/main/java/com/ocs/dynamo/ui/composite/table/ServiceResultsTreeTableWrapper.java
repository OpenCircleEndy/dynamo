/*
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package com.ocs.dynamo.ui.composite.table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ocs.dynamo.domain.AbstractEntity;
import com.ocs.dynamo.domain.model.EntityModel;
import com.ocs.dynamo.service.BaseService;
import com.ocs.dynamo.ui.Searchable;
import com.ocs.dynamo.ui.container.QueryType;
import com.ocs.dynamo.ui.container.ServiceContainer;
import com.ocs.dynamo.ui.container.hierarchical.HierarchicalContainer.HierarchicalDefinition;
import com.ocs.dynamo.ui.container.hierarchical.HierarchicalFetchJoinInformation;
import com.ocs.dynamo.ui.container.hierarchical.ModelBasedHierarchicalContainer;
import com.ocs.dynamo.ui.container.hierarchical.ModelBasedHierarchicalContainer.ModelBasedHierarchicalDefinition;
import com.vaadin.data.Container;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.sort.SortOrder;
import com.vaadin.ui.Table;

/**
 * Simple search of hierarchical information presented in tree table. Uses
 * ModelBasedHierachicalContainer, hence also those assumptions.
 * <p>
 * Additionally it will by default only generate search fields on the entity that is on the lowest
 * level of the hierarchy.
 * 
 * @author Patrick Deenen (patrick.deenen@opencirclesolutions.nl)
 */
public class ServiceResultsTreeTableWrapper<ID extends Serializable, T extends AbstractEntity<ID>>
        extends ServiceResultsTableWrapper<ID, T> {

    private static final long serialVersionUID = -9054619694421055983L;

    private List<BaseService<?, ?>> services;

    /**
     * Constructor
     * 
     * @param rootEntityModel
     * @param queryType
     * @param order
     * @param joins
     * @param services
     */
    @SuppressWarnings("unchecked")
    public ServiceResultsTreeTableWrapper(EntityModel<T> rootEntityModel, QueryType queryType,
            List<SortOrder> sortOrders, HierarchicalFetchJoinInformation[] joins,
            BaseService<?, ?>... services) {
        super((BaseService<ID, T>) services[0], rootEntityModel, queryType, null, sortOrders, joins);
        this.services = new ArrayList<>();
        this.services.addAll(Arrays.asList(services));
    }

    /**
     * Constructor
     * 
     * @param services
     * @param entityModel
     * @param queryType
     * @param order
     * @param joins
     */
    @SuppressWarnings("unchecked")
    public ServiceResultsTreeTableWrapper(List<BaseService<?, ?>> services,
            EntityModel<T> rootEntityModel, QueryType queryType, List<SortOrder> sortOrders,
            HierarchicalFetchJoinInformation[] joins) {
        super((BaseService<ID, T>) services.get(0), rootEntityModel, queryType, null, sortOrders,
                joins);
        this.services = services;
    }

    /**
     * Creates the container
     */
    @Override
    protected Container constructContainer() {
        return new ModelBasedHierarchicalContainer<T>(getMessageService(), getEntityModelFactory(),
                getEntityModel(), services, (HierarchicalFetchJoinInformation[]) getJoins(),
                getQueryType());
    }

    /**
     * 
     */
    @SuppressWarnings("unchecked")
    @Override
    public ModelBasedHierarchicalContainer<T> getContainer() {
        return (ModelBasedHierarchicalContainer<T>) super.getContainer();
    }

    @Override
    protected Table constructTable() {
        return new ModelBasedTreeTable<ID, T>(getContainer(), getEntityModelFactory());
    }

    @Override
    protected void initSortingAndFiltering() {
        if (!getContainer().getHierarchy().isEmpty()) {
            // get the definition on the lowest level
            HierarchicalDefinition def = getContainer().getHierarchy().get(
                    getContainer().getHierarchy().size() - 1);
            if (getFilter() != null && def.getContainer() instanceof ServiceContainer<?, ?>) {
                ((ServiceContainer<?, ?>) def.getContainer()).getQueryView().addFilter(getFilter());
            }
        }
        if (getSortOrders() != null && getSortOrders().size() > 0) {
            getTable().sort(getSortProperties(), getSortDirections());
        }
    }

    /**
     * Perform a search
     * 
     * @param filter
     *            the search filter
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void search(Filter filter) {
        if (getContainer() != null && !getContainer().getHierarchy().isEmpty()) {
            ModelBasedHierarchicalDefinition def = (ModelBasedHierarchicalDefinition) getContainer()
                    .getHierarchicalDefinition(0);
            if (def.getContainer() instanceof Searchable) {
                ((Searchable) def.getContainer()).search(filter);
            }
        }
    }
}
