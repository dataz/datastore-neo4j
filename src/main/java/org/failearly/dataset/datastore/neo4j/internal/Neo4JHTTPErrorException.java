/*
 * dataSet - Test Support For Data Stores.
 *
 * Copyright (C) 2014-2015 Marko Umek (http://fail-early.com/contact)
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

package org.failearly.dataset.datastore.neo4j.internal;

import org.failearly.dataset.datastore.DataStoreException;

import javax.ws.rs.core.Response;

/**
 * Neo4JDataSoreException thrown in case of any HTTP error.
 */
public class Neo4JHTTPErrorException extends DataStoreException {
    public Neo4JHTTPErrorException(Response response) {
        super("HTTP error: '" + response.toString() +"'");
    }
}
