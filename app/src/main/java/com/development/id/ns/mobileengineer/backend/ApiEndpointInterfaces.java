
/**
 * Created by Drago on 6/28/2017.
 */

package com.development.id.ns.mobileengineer.backend;

import com.development.id.ns.mobileengineer.backend.json.DemoItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndpointInterfaces {

    //////////// ITEMS /////////////
    @GET("items.json")
    Call<List<DemoItem>> getItems();

}
