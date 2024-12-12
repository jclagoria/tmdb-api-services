package com.tmdb.api.health;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

@Readiness
@ApplicationScoped
public class ReadinessCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        boolean isTmdbAvailable = checkTmdbAvailability();
        return isTmdbAvailable
                ? HealthCheckResponse.up("Tmdb API is ready")
                : HealthCheckResponse.down("Tmdb API is not ready");
    }

    private boolean checkTmdbAvailability() {
        return true;
    }
}
