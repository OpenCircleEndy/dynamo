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
package com.ocs.dynamo.domain.model.impl;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.ocs.dynamo.domain.model.AttributeDateType;
import com.ocs.dynamo.domain.model.AttributeModel;
import com.ocs.dynamo.domain.model.AttributeSelectMode;
import com.ocs.dynamo.domain.model.AttributeTextFieldMode;
import com.ocs.dynamo.domain.model.AttributeType;
import com.ocs.dynamo.domain.model.EntityModel;

/**
 * Implementation of the AttributeModel interface - simple container for properties
 * 
 * @author bas.rutten
 */
public class AttributeModelImpl implements AttributeModel {

    private Set<String> allowedExtensions = new HashSet<>();

    private AttributeType attributeType;

    private boolean complexEditable;

    private boolean currency;

    private AttributeDateType dateType;

    private Object defaultValue;

    private String description;

    private boolean detailFocus;

    private String displayFormat;

    private String displayName;

    private boolean email;

    private EntityModel<?> entityModel;

    private String falseRepresentation;

    private String fileNameProperty;

    private boolean image;

    private boolean mainAttribute;

    private Integer maxLength;

    private Class<?> memberType;

    private Integer minLength;

    private boolean multipleSearch;

    private String name;

    private EntityModel<?> nestedEntityModel;

    private Integer order;

    private boolean percentage;

    private int precision;

    private String prompt;

    private boolean quickAddAllowed;

    private String quickAddPropertyName;

    private boolean readOnly;

    private String replacementSearchPath;

    private boolean required;

    private boolean searchable;

    private boolean searchCaseSensitive;

    private boolean searchForExactValue;

    private boolean searchPrefixOnly;

    private AttributeSelectMode searchSelectMode;

    private AttributeSelectMode selectMode;

    private boolean sortable;

    private AttributeTextFieldMode textFieldMode;

    private String trueRepresentation;

    private Class<?> type;

    private boolean url;

    private boolean useThousandsGrouping;

    private boolean visible;

    private boolean visibleInTable;

    private boolean week;

    @Override
    public int compareTo(AttributeModel o) {
        return this.getOrder() - o.getOrder();
    }

    @Override
    public Set<String> getAllowedExtensions() {
        return allowedExtensions;
    }

    @Override
    public AttributeType getAttributeType() {
        return attributeType;
    }

    @Override
    public AttributeDateType getDateType() {
        return dateType;
    }

    @Override
    public Object getDefaultValue() {
        return defaultValue;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getDisplayFormat() {
        return displayFormat;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public EntityModel<?> getEntityModel() {
        return entityModel;
    }

    @Override
    public String getFalseRepresentation() {
        return falseRepresentation;
    }

    @Override
    public String getFileNameProperty() {
        return fileNameProperty;
    }

    @Override
    public Integer getMaxLength() {
        return maxLength;
    }

    @Override
    public Class<?> getMemberType() {
        return memberType;
    }

    @Override
    public Integer getMinLength() {
        return minLength;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public EntityModel<?> getNestedEntityModel() {
        return nestedEntityModel;
    }

    @Override
    public Integer getOrder() {
        return order;
    }

    @Override
    public String getPath() {
        String reference = entityModel.getReference();
        int p = reference.indexOf('.');

        if (p <= 0) {
            return name;
        } else {
            return reference.substring(p + 1) + "." + name;
        }
    }

    @Override
    public int getPrecision() {
        return precision;
    }

    @Override
    public String getPrompt() {
        return prompt;
    }

    @Override
    public String getQuickAddPropertyName() {
        return quickAddPropertyName;
    }

    @Override
    public String getReplacementSearchPath() {
        return replacementSearchPath;
    }

    @Override
    public AttributeSelectMode getSearchSelectMode() {
        return searchSelectMode;
    }

    @Override
    public AttributeSelectMode getSelectMode() {
        return selectMode;
    }

    @Override
    public AttributeTextFieldMode getTextFieldMode() {
        return textFieldMode;
    }

    @Override
    public String getTrueRepresentation() {
        return trueRepresentation;
    }

    @Override
    public Class<?> getType() {
        return type;
    }

    @Override
    public boolean isComplexEditable() {
        return complexEditable;
    }

    @Override
    public boolean isCurrency() {
        return currency;
    }

    @Override
    public boolean isDetailFocus() {
        return detailFocus;
    }

    @Override
    public boolean isEmail() {
        return email;
    }

    @Override
    public boolean isEmbedded() {
        return AttributeType.EMBEDDED.equals(attributeType);
    }

    @Override
    public boolean isImage() {
        return image;
    }

    @Override
    public boolean isMainAttribute() {
        return mainAttribute;
    }

    public boolean isMultipleSearch() {
        return multipleSearch;
    }

    @Override
    public boolean isNumerical() {
        return Number.class.isAssignableFrom(type);
    }

    @Override
    public boolean isPercentage() {
        return percentage;
    }

    @Override
    public boolean isQuickAddAllowed() {
        return quickAddAllowed;
    }

    @Override
    public boolean isReadOnly() {
        return readOnly;
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    @Override
    public boolean isSearchable() {
        return searchable;
    }

    @Override
    public boolean isSearchCaseSensitive() {
        return searchCaseSensitive;
    }

    @Override
    public boolean isSearchForExactValue() {
        return searchForExactValue;
    }

    @Override
    public boolean isSearchPrefixOnly() {
        return searchPrefixOnly;
    }

    @Override
    public boolean isSortable() {
        return sortable;
    }

    public boolean isUrl() {
        return url;
    }

    public boolean isUseThousandsGrouping() {
        return useThousandsGrouping;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public boolean isVisibleInTable() {
        return visibleInTable;
    }

    @Override
    public boolean isWeek() {
        return week;
    }

    public void setAllowedExtensions(Set<String> allowedExtensions) {
        this.allowedExtensions = allowedExtensions;
    }

    public void setAttributeType(AttributeType attributeType) {
        this.attributeType = attributeType;
    }

    public void setComplexEditable(boolean complexEditable) {
        this.complexEditable = complexEditable;
    }

    public void setCurrency(boolean currency) {
        this.currency = currency;
    }

    public void setDateType(AttributeDateType dateType) {
        this.dateType = dateType;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDetailFocus(boolean detailFocus) {
        this.detailFocus = detailFocus;
    }

    public void setDisplayFormat(String displayFormat) {
        this.displayFormat = displayFormat;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setEmail(boolean email) {
        this.email = email;
    }

    public void setEntityModel(EntityModel<?> entityModel) {
        this.entityModel = entityModel;
    }

    public void setFalseRepresentation(String falseRepresentation) {
        this.falseRepresentation = falseRepresentation;
    }

    public void setFileNameProperty(String fileNameProperty) {
        this.fileNameProperty = fileNameProperty;
    }

    public void setImage(boolean image) {
        this.image = image;
    }

    @Override
    public void setMainAttribute(boolean mainAttribute) {
        this.mainAttribute = mainAttribute;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public void setMemberType(Class<?> memberType) {
        this.memberType = memberType;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    public void setMultipleSearch(boolean multipleSearch) {
        this.multipleSearch = multipleSearch;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNestedEntityModel(EntityModel<?> nestedEntityModel) {
        this.nestedEntityModel = nestedEntityModel;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public void setPercentage(boolean percentage) {
        this.percentage = percentage;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public void setQuickAddAllowed(boolean quickAddAllowed) {
        this.quickAddAllowed = quickAddAllowed;
    }

    public void setQuickAddPropertyName(String quickAddPropertyName) {
        this.quickAddPropertyName = quickAddPropertyName;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public void setReplacementSearchPath(String replacementSearchPath) {
        this.replacementSearchPath = replacementSearchPath;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public void setSearchable(boolean searchable) {
        this.searchable = searchable;
    }

    public void setSearchCaseSensitive(boolean searchCaseSensitive) {
        this.searchCaseSensitive = searchCaseSensitive;
    }

    public void setSearchForExactValue(boolean searchForExactValue) {
        this.searchForExactValue = searchForExactValue;
    }

    public void setSearchPrefixOnly(boolean searchPrefixOnly) {
        this.searchPrefixOnly = searchPrefixOnly;
    }

    public void setSearchSelectMode(AttributeSelectMode searchSelectMode) {
        this.searchSelectMode = searchSelectMode;
    }

    public void setSelectMode(AttributeSelectMode selectMode) {
        this.selectMode = selectMode;
    }

    public void setSortable(boolean sortable) {
        this.sortable = sortable;
    }

    public void setTextFieldMode(AttributeTextFieldMode textFieldMode) {
        this.textFieldMode = textFieldMode;
    }

    public void setTrueRepresentation(String trueRepresentation) {
        this.trueRepresentation = trueRepresentation;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public void setUrl(boolean url) {
        this.url = url;
    }

    public void setUseThousandsGrouping(boolean useThousandsGrouping) {
        this.useThousandsGrouping = useThousandsGrouping;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setVisibleInTable(boolean visibleInTable) {
        this.visibleInTable = visibleInTable;
    }

    public void setWeek(boolean week) {
        this.week = week;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toStringExclude(this, "entityModel");
    }
}
