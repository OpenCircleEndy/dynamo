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
package com.ocs.dynamo.ui.converter;

import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

import com.ocs.dynamo.utils.DateUtils;
import com.vaadin.data.util.converter.Converter;

/**
 * Converter for translating a week code (yyyy-ww) to a Date. We need custom functionality for this
 * since the default SimpleDateFormat does not handle a situation in which the first days of the
 * first week of a year fall within the previous yes
 * 
 * @author bas.rutten
 */
public class WeekCodeConverter implements Converter<String, Date> {

    private static final long serialVersionUID = 8190701526190914057L;

    /**
     * Conver to model
     */
    @Override
    public Date convertToModel(String value, Class<? extends Date> targetType, Locale locale) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return DateUtils.getStartDateOfWeek(value);
    }

    /**
     * Convert to presentation
     */
    @Override
    public String convertToPresentation(Date value, Class<? extends String> targetType,
            Locale locale) {
        if (value == null) {
            return null;
        }
        return DateUtils.toWeekCode(value);
    }

    @Override
    public Class<Date> getModelType() {
        return Date.class;
    }

    @Override
    public Class<String> getPresentationType() {
        return String.class;
    }

}
