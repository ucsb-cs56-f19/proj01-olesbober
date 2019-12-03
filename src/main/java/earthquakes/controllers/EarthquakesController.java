package earthquakes.controllers;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import earthquakes.geojson.FeatureCollection;
import earthquakes.services.EarthquakeQueryService;
import earthquakes.searches.EqSearch;

@Controller
public class EarthquakesController {

    @GetMapping("/earthquakes/search")
    public String getEarthquakesSearch(Model model, OAuth2AuthenticationToken oAuth2AuthenticationToken, EqSearch eqSearch) {
        return "earthquakes/search";
    }

    @GetMapping("/earthquakes/results")
    public String getEarthquakesResults(Model model, OAuth2AuthenticationToken oAuth2AuthenticationToken, EqSearch eqSearch) {

        EarthquakeQueryService e = new EarthquakeQueryService();
        model.addAttribute("eqSearch", eqSearch);
        String json = e.getJSON(eqSearch.getDistance(), eqSearch.getMinmag(), eqSearch.getLat(), eqSearch.getLon());
        model.addAttribute("json", json);
        FeatureCollection featureCollection = FeatureCollection.fromJSON(json);
        model.addAttribute("featureCollection",featureCollection);
        return "earthquakes/results";
    }
    
}
