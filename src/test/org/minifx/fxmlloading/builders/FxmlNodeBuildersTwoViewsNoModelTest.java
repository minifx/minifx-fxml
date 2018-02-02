/**
 * Copyright (c) 2018 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package org.minifx.fxmlloading.builders;

import static org.junit.Assert.*;

import org.junit.Test;
import org.minifx.fxmlloading.factories.ModelSharingControllerFactory;
import org.minifx.fxmlloading.lang.nomodel.SimpleViewController;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class FxmlNodeBuildersTwoViewsNoModelTest extends ApplicationTest {

    @Test
    public void usageExamples() throws InterruptedException {
        Thread.sleep(10000);
        assertTrue(true);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(rootNode()));
        stage.show();
    }

    private Parent rootNode() {
        ModelSharingControllerFactory factory = ModelSharingControllerFactory.newDefault();

        TabPane tab = new TabPane();
        Parent node1 = FxmlNodeBuilders.byConventionFrom(SimpleViewController.class).controllersFrom(factory).build();
        Parent node2 = FxmlNodeBuilders.byConventionFrom(SimpleViewController.class).controllersFrom(factory).build();

        tab.getTabs().addAll(new Tab("a", node1), new Tab("b", node2));
        return tab;
    }

}
