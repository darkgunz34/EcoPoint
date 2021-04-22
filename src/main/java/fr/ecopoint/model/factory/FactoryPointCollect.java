package fr.ecopoint.model.factory;

import fr.ecopoint.model.entities.PointCollect;

public final class FactoryPointCollect {
    private FactoryPointCollect(){

    }

    public static PointCollect getPointCollect(int nbPoint){
        return new PointCollect(nbPoint);
    }

}
