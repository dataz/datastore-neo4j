/*
 * dataZ - Test Support For Data Stores.
 *
 * Copyright (C) 2014-2016 'Marko Umek' (http://fail-early.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package org.failearly.dataz.datastore.neo4j.internal;

import org.failearly.common.message.*;
import org.failearly.dataz.datastore.DataStoreBase;

/**
 * Neo4JEstablishingConnectionFailedMessage is responsible for ...
 */
@ClasspathMessageTemplate
@TemplateParameters({
        Neo4JEstablishingConnectionFailedMessage.ARG_URL,
        Neo4JEstablishingConnectionFailedMessage.ARG_DS_ID,
        Neo4JEstablishingConnectionFailedMessage.ARG_URL_PROPERTY,
        Neo4JEstablishingConnectionFailedMessage.ARG_CONFIG_FILE
    })
final class Neo4JEstablishingConnectionFailedMessage extends MessageBuilderBase<Neo4JEstablishingConnectionFailedMessage> {
    static final String ARG_URL = "url";
    static final String ARG_DS_ID = "dsid";
    static final String ARG_URL_PROPERTY = "urlproperty";
    static final String ARG_CONFIG_FILE = "configfile";

    private Neo4JEstablishingConnectionFailedMessage() {
        super(Neo4JEstablishingConnectionFailedMessage.class);
    }

    static Message create(MessageBuilder.Initializer<Neo4JEstablishingConnectionFailedMessage> initializer) {
        return new Neo4JEstablishingConnectionFailedMessage().buildLazyMessage(initializer);
    }

    Neo4JEstablishingConnectionFailedMessage withDataStore(DataStoreBase dataStore) {
        return with(ARG_DS_ID, dataStore.getId()).with(ARG_CONFIG_FILE, dataStore.getConfigFile());
    }

    Neo4JEstablishingConnectionFailedMessage withUrl(String url) {
        return with(ARG_URL, url);
    }

    Neo4JEstablishingConnectionFailedMessage withUrlProperty(String urlProperty) {
        return with(ARG_URL_PROPERTY, urlProperty);
    }


}
