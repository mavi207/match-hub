package com.example.matchhub.controllerlayer;

import com.example.matchhub.servicelayer.impl.VenueServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("venue")
public class VenueController {

        private final VenueServiceImpl venueServiceimpl;

        public VenueController(VenueServiceImpl venueServiceimpl) {
                this.venueServiceimpl = venueServiceimpl;
        }
}
