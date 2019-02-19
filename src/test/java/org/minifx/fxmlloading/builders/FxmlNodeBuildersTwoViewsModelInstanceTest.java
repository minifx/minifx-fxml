/**
 * Copyright (c) 2018 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package org.minifx.fxmlloading.builders;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.minifx.fxmlloading.lang.instance.SimpleViewController;
import org.minifx.fxmlloading.lang.instance.SimpleViewValueModel;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class FxmlNodeBuildersTwoViewsModelInstanceTest extends ApplicationTest {

    private Parent node2;
    private Parent node1;

    @Test
    public void explicitlySharedModelsOfRepeatedViewAreShared() {
        Node sliderController1 = node1.lookup("#slider1");
        Label labelController1 = (Label) node1.lookup("#label1");
        Label labelController2 = (Label) node2.lookup("#label1");

        assertThat(labelController1.getText()).isEqualTo("0.0");
        assertThat(labelController2.getText()).isEqualTo("0.0");

        clickOn(sliderController1);

        assertThat(labelController1.getText()).isNotEqualTo("0.0");
        assertThat(labelController1.getText()).isEqualTo(labelController2.getText());
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(rootNode()));
        stage.show();
    }

    private Parent rootNode() {
        TabPane tab = new TabPane();

        SimpleViewValueModel model = new SimpleViewValueModel();
        node1 = FxmlNodeBuilders.byConventionFrom(new SimpleViewController(model)).build();
        node2 = FxmlNodeBuilders.byConventionFrom(new SimpleViewController(model)).build();

        tab.getTabs().addAll(new Tab("a", node1), new Tab("b", node2));
        return tab;
    }

}
