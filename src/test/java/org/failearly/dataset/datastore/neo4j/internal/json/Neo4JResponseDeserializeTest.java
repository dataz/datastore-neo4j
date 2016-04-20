/*
 * dataZ - Test Support For Data Stores.
 *
 * Copyright (C) 2014-2016 marko (http://fail-early.com)
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
 *
 */

package org.failearly.dataset.datastore.neo4j.internal.json;

import org.failearly.dataset.datastore.neo4j.internal.Neo4JDataStoreException;
import org.failearly.common.test.ExceptionVerifier;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Neo4JResultsTest contains tests for ... .
 */
public class Neo4JResponseDeserializeTest {

    @Test
    public void simple_response_from_multiple_statements_request() throws Exception {
        // arrange / given
        final String json = "{\"results\":[{\"columns\":[],\"data\":[]},{\"columns\":[],\"data\":[]}],\"errors\":[]}";

        // act / when
        final Neo4JResponse response = Neo4JResponse.fromJson(json);

        // assert / then
        assertTrue("No error?", response.isOk());
        // No exception
        response.throwOnErrors();
    }

    @Test
    public void response_with_native_array_result() throws Exception {
        // arrange / given
        final String json = "{\n" +
                "  \"results\" : [ {\n" +
                "    \"columns\" : [ \"id(n)\" ],\n" +
                "    \"data\" : [ {\n" +
                "      \"row\" : [ 14, \"12\", 15 ]\n" +
                "    } ]\n" +
                "  } ],\n" +
                "  \"errors\" : [ ]\n" +
                "}";

        // act / when
        final Neo4JResponse response = Neo4JResponse.fromJson(json);

        // assert / then
        assertTrue("No error?", response.isOk());
        // No exception
        response.throwOnErrors();
    }

    @Test
    public void complex_response() throws Exception {
        // arrange / given
        final String json = "{\n" +
                "  \"results\" : [ {\n" +
                "    \"columns\" : [ \"bike\", \"p1\", \"p2\" ],\n" +
                "    \"data\" : [ {\n" +
                "      \"row\" : [ {\n" +
                "        \"weight\" : 10\n" +
                "      }, [ {\n" +
                "        \"weight\" : 10\n" +
                "      }, {\n" +
                "        \"position\" : 1\n" +
                "      }, {\n" +
                "        \"spokes\" : 3\n" +
                "      } ], [ {\n" +
                "        \"weight\" : 10\n" +
                "      }, {\n" +
                "        \"position\" : 2\n" +
                "      }, {\n" +
                "        \"spokes\" : 32\n" +
                "      } ] ],\n" +
                "      \"graph\" : {\n" +
                "        \"nodes\" : [ {\n" +
                "          \"id\" : \"17\",\n" +
                "          \"labels\" : [ \"Wheel\" ],\n" +
                "          \"properties\" : {\n" +
                "            \"spokes\" : 3\n" +
                "          }\n" +
                "        }, {\n" +
                "          \"id\" : \"16\",\n" +
                "          \"labels\" : [ \"Bike\" ],\n" +
                "          \"properties\" : {\n" +
                "            \"weight\" : 10\n" +
                "          }\n" +
                "        }, {\n" +
                "          \"id\" : \"18\",\n" +
                "          \"labels\" : [ \"Wheel\" ],\n" +
                "          \"properties\" : {\n" +
                "            \"spokes\" : 32\n" +
                "          }\n" +
                "        } ],\n" +
                "        \"relationships\" : [ {\n" +
                "          \"id\" : \"9\",\n" +
                "          \"type\" : \"HAS\",\n" +
                "          \"startNode\" : \"16\",\n" +
                "          \"endNode\" : \"17\",\n" +
                "          \"properties\" : {\n" +
                "            \"position\" : 1\n" +
                "          }\n" +
                "        }, {\n" +
                "          \"id\" : \"10\",\n" +
                "          \"type\" : \"HAS\",\n" +
                "          \"startNode\" : \"16\",\n" +
                "          \"endNode\" : \"18\",\n" +
                "          \"properties\" : {\n" +
                "            \"position\" : 2\n" +
                "          }\n" +
                "        } ]\n" +
                "      }\n" +
                "    } ]\n" +
                "  } ],\n" +
                "  \"errors\" : [ ]\n" +
                "}";

        // act / when
        final Neo4JResponse response = Neo4JResponse.fromJson(json);

        // assert / then
        assertTrue("No error?", response.isOk());
        // No exception
        response.throwOnErrors();
    }


    @Test
    public void response_with_error__should_throw_exception() throws Exception {
        // arrange / given
        final String json = "{\"results\":[{\"columns\":[],\"data\":[]},{\"columns\":[],\"data\":[]}]," +
                "\"errors\":[" +
                "{\"code\":\"Neo.ClientError (...)\"" +
                ",\"message\":\"Invalid input 'X': expected <init> (line 1, column 1 (offset: 0))^\"}" +
                "]}";

        // act / when
        final Neo4JResponse response = Neo4JResponse.fromJson(json);

        // assert / then
        assertFalse("Error?", response.isOk());
        ExceptionVerifier.on(response::throwOnErrors).expect(Neo4JDataStoreException.class).expect("Neo4J Datastore complains about 1 error(s):\n\tNeo4JError{code='Neo.ClientError (...)', message='Invalid input 'X': expected <init> (line 1, column 1 (offset: 0))^'}\n").verify();
    }

    @Test
    public void response_with_unknown_properties__should_be_accepted__and__the_properties_ignored() throws Exception {
        // arrange / given
        final String json = "{\"unknownArray\":[],\"unknownObject\":{},\"results\":[{\"columns\":[],\"data\":[]}],\"errors\":[]}";

        // act / when
        final Neo4JResponse response = Neo4JResponse.fromJson(json);

        // assert / then
        assertTrue("No error?", response.isOk());
        // No exception
        response.throwOnErrors();
    }



}