package org.ccci.gto.servicemix.globalreg.camel;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

public class CamelCxfUtilsTest {
    @Test
    public void testHasValidQueryParams() throws Exception {
        final String[] params = {"filters[owned_by]", "filters[owned_by][]"};

        // test no owned_by filters
        final MultivaluedMap<String, String> query = new MultivaluedHashMap<>();
        assertFalse(CamelCxfUtils.hasValidQueryParams(query, params));
        query.add("filters[first_name]", "bobby");
        assertFalse(CamelCxfUtils.hasValidQueryParams(query, params));
        query.add("filters[last_name]", "tables");
        assertFalse(CamelCxfUtils.hasValidQueryParams(query, params));

        // test blank owned_by filters
        query.add("filters[owned_by]", null); // is this even possible normally?
        assertFalse(CamelCxfUtils.hasValidQueryParams(query, params));
        query.add("filters[owned_by]", "");
        assertFalse(CamelCxfUtils.hasValidQueryParams(query, params));
        query.add("filters[owned_by]", " ");
        assertFalse(CamelCxfUtils.hasValidQueryParams(query, params));
        query.add("filters[owned_by]", "     ");
        assertFalse(CamelCxfUtils.hasValidQueryParams(query, params));
        query.add("filters[owned_by][]", null); // is this even possible normally?
        assertFalse(CamelCxfUtils.hasValidQueryParams(query, params));
        query.add("filters[owned_by][]", "");
        assertFalse(CamelCxfUtils.hasValidQueryParams(query, params));
        query.add("filters[owned_by][]", " ");
        assertFalse(CamelCxfUtils.hasValidQueryParams(query, params));
        query.add("filters[owned_by][]", "     ");
        assertFalse(CamelCxfUtils.hasValidQueryParams(query, params));

        // test "all" owned_by filters
        query.add("filters[owned_by]", "all");
        assertTrue(CamelCxfUtils.hasValidQueryParams(query, params));
        query.add("filters[owned_by][]", "all");
        assertTrue(CamelCxfUtils.hasValidQueryParams(query, params));
        query.remove("filters[owned_by]");
        assertTrue(CamelCxfUtils.hasValidQueryParams(query, params));
        query.remove("filters[owned_by][]");
        assertFalse(CamelCxfUtils.hasValidQueryParams(query, params));

        // test uuid owned_by filter
        query.add("filters[owned_by]", "7fd5f45c-e75f-11e3-a5ef-12725f8f377c");
        assertTrue(CamelCxfUtils.hasValidQueryParams(query, params));
        query.add("filters[owned_by][]", "7fd5f45c-e75f-11e3-a5ef-12725f8f377c");
        assertTrue(CamelCxfUtils.hasValidQueryParams(query, params));
        query.remove("filters[owned_by]");
        assertTrue(CamelCxfUtils.hasValidQueryParams(query, params));
        query.remove("filters[owned_by][]");
        assertFalse(CamelCxfUtils.hasValidQueryParams(query, params));
    }
}
