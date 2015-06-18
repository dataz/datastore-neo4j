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

package org.failearly.dataset.datastore.neo4j.internal.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.failearly.dataset.datastore.neo4j.internal.Neo4JDataStoreException;
import org.junit.Ignore;
import org.junit.Test;

import static org.failearly.dataset.test.TestUtils.assertException;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

/**
 * Neo4JResultsTest contains tests for ... .
 */
public class Neo4JResultsDeserializeTest {

    private static final ObjectMapper mapper=new ObjectMapper();

    @Test
    public void neo4j_without_error__should_result_in_valid_Neo4JResults_object__and__no_exception() throws Exception {
        // arrange / given
        final String json="{\"results\":[{\"columns\":[],\"data\":[]},{\"columns\":[],\"data\":[]}],\"errors\":[]}";

        // act / when
        final Neo4JResults results=Neo4JResults.fromJson(json);

        // assert / then
        assertTrue("No error?", results.isOk());
        // No exception
        results.throwOnErrors();
    }

    @Test
    public void neo4j_with_error__should_throw_exception() throws Exception {
        // arrange / given
        final String json="{\"results\":[{\"columns\":[],\"data\":[]},{\"columns\":[],\"data\":[]}]," +
                "\"errors\":[" +
                    "{\"code\":\"Neo.ClientError (...)\"" +
                    ",\"message\":\"Invalid input 'X': expected <init> (line 1, column 1 (offset: 0))^\"}" +
                "]}";

        // act / when
        final Neo4JResults results=Neo4JResults.fromJson(json);

        // assert / then
        assertFalse("Error?", results.isOk());
        assertException(
                Neo4JDataStoreException.class,
                "Neo4J Datastore complains about 1 error(s):\n\tNeo4JError{code='Neo.ClientError (...)', message='Invalid input 'X': expected <init> (line 1, column 1 (offset: 0))^'}\n",
                results::throwOnErrors
        );
    }
}