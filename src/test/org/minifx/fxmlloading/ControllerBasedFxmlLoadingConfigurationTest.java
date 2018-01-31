/**
 * Copyright (c) 2018 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package org.minifx.fxmlloading;

import static org.junit.Assert.*;

import org.junit.Test;
import org.minifx.fxmlloading.configuration.FxmlLoadingConfiguration;
import org.minifx.fxmlloading.configuration.impl.ControllerBasedFxmlLoadingConfiguration;

public class ControllerBasedFxmlLoadingConfigurationTest {

    @Test
    public void test() {
        FxmlLoadingConfiguration convention = ControllerBasedFxmlLoadingConfiguration.of(ControllerBasedFxmlLoadingConfigurationTest.class);
       // System.out.println(convention.bundleName());

    }

}
