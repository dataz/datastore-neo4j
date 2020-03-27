/*
 * dataZ - Test Support For Data Stores.
 *
 * Copyright 2014-2020 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

package org.failearly.dataz.datastore.neo4j.internal;

import org.failearly.dataz.datastore.DataStoreException;

import javax.ws.rs.core.Response;

/**
 * Neo4JDataSoreException thrown in case of any HTTP error.
 */
public class Neo4JHTTPErrorException extends DataStoreException {
    public Neo4JHTTPErrorException(Response response) {
        super("HTTP error: '" + response.toString() +"'");
    }
}
