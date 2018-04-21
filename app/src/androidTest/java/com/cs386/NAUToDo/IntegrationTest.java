package com.cs386.NAUToDo;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import static junit.framework.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collections;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class IntegrationTest
{
    private RecyclerAdapter testRecyclerAdapter;
    private List<ListItem> data = Collections.emptyList();

    @Before
    public void setUp()
    {
        Context ctx = InstrumentationRegistry.getContext();
        testRecyclerAdapter = new RecyclerAdapter( ctx, data );
    }

    @Test
    public void testGetItemCount() {
        int result = testRecyclerAdapter.getItemCount();
        assertEquals( 0, result);
    }
}
