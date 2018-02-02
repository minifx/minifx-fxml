/**
 * Copyright (c) 2018 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package org.minifx.fxmlloading.builders;

import javafx.scene.Node;

/**
 * An interface for builders that allow to build nodes. Additionally to the common build method, they provide a method
 * to query if the build will be possible, or not ({@link #canBuild()}).
 *
 * @author kfuchsbe
 */
public interface FxmlNodeBuilder {

    /**
     * Creates a new node, as described by preceding options given to the builder.
     *
     * @return the new instance of the node
     */
    public Node build();

    /**
     * Can be (optionally) called before the build method to check if the call to {@link #build()} will be successful.
     * Even if this returns {@code true} it is not guaranteed that the {@link #build()} method will for sure succeed
     * (not throw an exception, but this method is more intended to check if with the options to the builder it makes
     * sense at all to call the build method.
     *
     * @return {@code true} if a call to {@link #build()} is promising, {@code false} otherwise
     */
    public boolean canBuild();

}
