package io.andromeda.pippo.routes;

import io.andromeda.pippo.routes.fields.Field;
import io.andromeda.pippo.routes.fields.FullNameField;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 19.03.2017.
 */
public class ContactRouteTest {

    @Test
    public void testFragmentTags() {
        List<Field> fields = new ArrayList<>();
        fields.add(new FullNameField(true));

        //assertThat(template, equalTo(expectedTemplate));
    }

}
