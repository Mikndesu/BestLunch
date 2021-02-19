package com.mikn.bestlunch;

// This program is converted from https://www.egao-inc.co.jp/programming/swift_position/
public class Hubeny {
    private final double lat1;
    private final double lon1;
    private final double lat2;
    private final double lon2;

    Hubeny(double lat1, double lon1, double lat2, double lon2) {
        this.lat1 = degToRad(lat1);
        this.lon1 = degToRad(lon1);
        this.lat2 = degToRad(lat2);
        this.lon2 = degToRad(lon2);
    }

    // convert latitude and longitude to rad
    private double degToRad(double deg) {
        return deg * Math.PI / 180.0;
    }

    public double distance() {
        double LATDIFF = lat2 - lat1;
        double LONDIFF = lon2 - lon1;
        double LATAVE = (lat1 + lat2) / 2.0;
        double A = 6378137.0;
        double B = 6356752.314140356;
        double E2 = (A * A - B * B) / (A * A);
        double A1E2 = A * (1 - E2);
        double SINLAT = Math.sin(LATAVE);
        double W2 = 1.0 - E2 * (SINLAT * SINLAT);
        double M = A1E2 / (Math.sqrt(W2) * W2);
        double N = A / Math.sqrt(W2);
        double T1 = M * LATDIFF;
        double T2 = N * Math.cos(LATAVE) * LONDIFF;
        double accValue = Math.sqrt((T1 * T1) + (T2 * T2));
        return (Math.round(accValue * 10.0)) / 10;
    }
}
