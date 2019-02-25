[![Codacy Badge](https://api.codacy.com/project/badge/Grade/658f3b48e0e14020b6ab5424c56f271b)](https://app.codacy.com/app/minifx-developers/minifx-fxml?utm_source=github.com&utm_medium=referral&utm_content=minifx/minifx-fxml&utm_campaign=Badge_Grade_Dashboard)
[![GitHub release](https://img.shields.io/github/release/minifx/minifx-fxml.svg)](https://github.com/minifx/minifx-fxml/releases/)
[![Build Status](https://travis-ci.com/minifx/minifx-fxml.svg?branch=master)](https://travis-ci.com/minifx/minifx-fxml)
[![License](https://img.shields.io/github/license/minifx/minifx-fxml.svg)](https://opensource.org/licenses/Apache-2.0) 
[![codecov](https://codecov.io/gh/minifx/minifx-fxml/branch/master/graph/badge.svg)](https://codecov.io/gh/minifx/minifx-fxml)


This package is maintained as part of the [minifx](https://github.com/minifx) organization on github. The main web page 
of minifx can be found [here](https://minifx.org).

## Motivation

This library simplifies fxml loading by conventions.

The basic ideas for this small library are the same than that of Adam Biens 
[afterburner.fx](https://github.com/AdamBien/afterburner.fx): fxml files, their controllers and (optionally) css files
are loaded by naming conventions. However, this library provides a bit more finegrained control over the objects 
to be injected into the controller objects themselves.

## Getting Started

To use minifx-fxml in your applications you have to add one of the following to your build files:

Maven:
```xml
<dependency>
    <groupId>org.minifx</groupId>
    <artifactId>minifx-fxml</artifactId>
    <version>X.Y.Z</version>
</dependency>
```

Gradle:
```groovy
compile 'org.minifx:minifx-fxml:X.Y.Z'
```

Hereby ```X.Y.Z``` is the latest version which can be found at the top of this page.
 
### The Conventions

As in afterburner, always the following files (assuming to be in the same package) belong together and will be loaded 
together by convention:   

* ```AnyView.fxml``` (mandatory)
* ```AnyViewController.java``` (mandatory)
* ```AnyView.css```

Here```AnyView``` has to be read as a placeholder for any other name.

__NOTE__
> ```AnyViewController``` has to be also set as value for the xml attribute ```fx:controller``` within the 
```AnyView.fxml``` file. This is in some cases a bit redundant.

### FxmlMagicNodes - Common Use Cases

The starting point for fluent clauses for the most common use cases to load views from fxml 
are provided by the ```FxmlMagicNodes``` class.


#### Isolated Model
 
As all the required information is contained in the fxml file, we can simply load the view by:

```java
Parent anyView = FxmlMagicNodes.isolatedWiredNode().fromFxml("/path/to/AnyView.fxml");
``` 

This will load the view defined in fxml file, assuming the following simple conventions:

* A new instance of the controller is created, assuming that the controller class has a now argument constructor. 
If this is not the case, the loading will fail.
* All fields which are annotated with the ```@Inject``` annotation are wired also by constructing new instances 
using new argument constructors. This is done recursively. (We refer to those injected objects as the "model" in the following)
* Nested views are supported and constructed the same way.
* If a css file following the naming convention is available, it will also be loaded.

This implies that if another view of the same type would be created, then the two views would not share any information. 
This is a good assumption for many cases.

The same can be achieved by using the the controller class as parameter, which might be easier and/or more refactoring safe:

```java
Parent anyView = FxmlMagicNodes.isolatedWiredNode().byConventionFrom(AnyViewController.class);
``` 

#### Shared Model

In some other cases it is desirable to share models between views. In the extreme case that all model objects of the 
same type shall be shared between views in the same jvm, the following can be used:

```java
Parent anyView = FxmlMagicNodes.globallyWiredNode().fromFxml("/path/to/AnyView.fxml");
``` 

This means that, if a second instance of the same view would be created all the model objects would be shared between them. 

__NOTE__
> The Controllers themselves are still instantiated per view instance! Otherwise a proper binding between xml and 
controllers is not possible.


### FxmlNodeBuilders - fine tuning node creation

Starting points for builder fluent clauses with more fine-grained control over the injected instances 
are housed in the ```FxmlNodeBuilders``` class. On purpose, with these builders, no defaults are assumed and all 
information has to be always provided.
 
The main two required information parts are:

* From which files to load the view
* How to inject controllers and model objects (herein lays the big variation range)

The equivalent of our first example, this time using builders would be:

```java
Parent anyView = FxmlNodeBuilders.fromFxml("/path/to/AnyView.fxml")
                                 .controllersFrom(ModelSharingControllerFactory.newDefault())
                                 .build();
```

The main new concept here is the ```ControllerFactory```, who is responsible for creating the instances for controllers 
and model objects. 

__NOTE__
> Despite the used factory is called ModelSharingControllerFactory, it only shares the model objects if it is reused.
Therefore, as we use a new instance here, it creates an isolated node. All in your hands here ;-)

Other options here are:

| factory clause | effect |
| ---------------| -------|
| ```controllersFrom(Callback<Class<?>, Object> controllerFactory)``` | retrieve the controllers and model objects from the passed int callback (e.g. a lambda expression)
| ```controllers(Iterable<?> controllerInstances)``` | pick the controllers from the iterable if their final class is the required one |
| ```controllers(Object... controllerInstances)``` | convenience method for the above one |

 ## Build Artifacts of the Latest Version
 
* [dependency license report](https://minifx.org/minifx-fxml/dependency-license/index.html)
* [junit test report](https://minifx.org/minifx-fxml/tests/test/index.html)
* [jacoco test report](https://minifx.org/minifx-fxml/jacoco/test/html/index.html) 
* [javadoc](https://minifx.org/minifx-fxml/javadoc/index.html) 
 
 