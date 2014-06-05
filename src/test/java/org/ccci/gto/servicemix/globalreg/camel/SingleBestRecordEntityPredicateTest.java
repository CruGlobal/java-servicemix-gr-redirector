package org.ccci.gto.servicemix.globalreg.camel;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

public class SingleBestRecordEntityPredicateTest {
    @Test
    public void testHasOwnedByFilters() throws Exception {
        final SingleBestRecordEntityPredicate sbrPredicate = new SingleBestRecordEntityPredicate();

        // test no owned_by filters
        final MultivaluedMap<String, String> query = new MultivaluedHashMap<>();
        assertFalse(sbrPredicate.hasOwnedByFilters(query));
        query.add("filters[first_name]", "bobby");
        assertFalse(sbrPredicate.hasOwnedByFilters(query));
        query.add("filters[last_name]", "tables");
        assertFalse(sbrPredicate.hasOwnedByFilters(query));

        // test blank owned_by filters
        query.add("filters[owned_by]", null); // is this even possible normally?
        assertFalse(sbrPredicate.hasOwnedByFilters(query));
        query.add("filters[owned_by]", "");
        assertFalse(sbrPredicate.hasOwnedByFilters(query));
        query.add("filters[owned_by]", " ");
        assertFalse(sbrPredicate.hasOwnedByFilters(query));
        query.add("filters[owned_by]", "     ");
        assertFalse(sbrPredicate.hasOwnedByFilters(query));
        query.add("filters[owned_by][]", null); // is this even possible normally?
        assertFalse(sbrPredicate.hasOwnedByFilters(query));
        query.add("filters[owned_by][]", "");
        assertFalse(sbrPredicate.hasOwnedByFilters(query));
        query.add("filters[owned_by][]", " ");
        assertFalse(sbrPredicate.hasOwnedByFilters(query));
        query.add("filters[owned_by][]", "     ");
        assertFalse(sbrPredicate.hasOwnedByFilters(query));

        // test "all" owned_by filters
        query.add("filters[owned_by]", "all");
        assertTrue(sbrPredicate.hasOwnedByFilters(query));
        query.add("filters[owned_by][]", "all");
        assertTrue(sbrPredicate.hasOwnedByFilters(query));
        query.remove("filters[owned_by]");
        assertTrue(sbrPredicate.hasOwnedByFilters(query));
        query.remove("filters[owned_by][]");
        assertFalse(sbrPredicate.hasOwnedByFilters(query));

        // test uuid owned_by filter
        query.add("filters[owned_by]", "7fd5f45c-e75f-11e3-a5ef-12725f8f377c");
        assertTrue(sbrPredicate.hasOwnedByFilters(query));
        query.add("filters[owned_by][]", "7fd5f45c-e75f-11e3-a5ef-12725f8f377c");
        assertTrue(sbrPredicate.hasOwnedByFilters(query));
        query.remove("filters[owned_by]");
        assertTrue(sbrPredicate.hasOwnedByFilters(query));
        query.remove("filters[owned_by][]");
        assertFalse(sbrPredicate.hasOwnedByFilters(query));
    }
}
