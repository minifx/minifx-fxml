/**
 * Copyright (c) 2018 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package org.minifx.fxmlloading.lang.instance;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class SimpleViewValueModel {
    private final DoubleProperty sliderValue = new SimpleDoubleProperty(0.0);

    public DoubleProperty sliderValueProperty() {
        return sliderValue;
    }

}
