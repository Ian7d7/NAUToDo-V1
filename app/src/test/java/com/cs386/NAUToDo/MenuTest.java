package com.cs386.NAUToDo;
import android.view.Menu;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class MenuTest
{
    @Mock
    private Menu testMenu;
    private MyListsActivity testList;

    @Before
    public void setUp ()
    {
        testList = new MyListsActivity();
    }

    @Test
    public void onCreateOptionsMenuReturnTrue() {
        boolean result = testList.onCreateOptionsMenu( testMenu );
        assertTrue(result);
    }
}
