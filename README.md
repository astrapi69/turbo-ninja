## Overview

The library turbo-ninja is a collection of Apache Wicket components and utilities. 

The components are loosely coupled and can be easily integrated into an existing Apache Wicket application.

The components are also designed to be customised so the user can provide they own components over factory methods.

## License

The source code comes under the liberal Apache License V2.0, making turbo-ninja great for all types of wicket applications.

# Build status

[![Build Status](https://travis-ci.org/astrapi69/turbo-ninja.svg?branch=master)](https://travis-ci.org/astrapi69/turbo-ninja)

## Maven Central

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/de.alpharogroup/turbo-ninja/badge.svg)](https://maven-badges.herokuapp.com/maven-central/de.alpharogroup/turbo-ninja)

## Maven projects and install

You can add the following maven dependencies to your project `pom.xml` if you want to import the library. 

You can first define the version properties:

```
<properties>
	...
	<!-- TURBO-NINJA version -->
	<turbo-ninja.version>6.25.2</turbo-ninja.version>
	<turbo-sansa-wicket-components.version>${turbo-ninja.version}</turbo-sansa-wicket-components.version>
	<wicket-bootstrap2.version>${turbo-ninja.version}</wicket-bootstrap2.version>
	<wicket-bootstrap3.version>${turbo-ninja.version}</wicket-bootstrap3.version>
	<wicket-component-expressions.version>${turbo-ninja.version}</wicket-component-expressions.version>
	...
</properties>
```

Add the following maven dependency to your project `pom.xml` if you want to import turbo-sansa-wicket-components:

```xml
<dependency>
	<groupId>de.alpharogroup</groupId>
	<artifactId>turbo-sansa-wicket-components</artifactId>
	<version>${turbo-sansa-wicket-components.version}</version>
</dependency>
```

Add the following maven dependency to your project `pom.xml` if you want to import wicket-bootstrap2:

```xml
<dependency>
	<groupId>de.alpharogroup</groupId>
	<artifactId>wicket-bootstrap2</artifactId>
	<version>${wicket-bootstrap2.version}</version>
</dependency>
```

Add the following maven dependency to your project `pom.xml` if you want to import wicket-bootstrap3:

```xml
<dependency>
	<groupId>de.alpharogroup</groupId>
	<artifactId>wicket-bootstrap3</artifactId>
	<version>${wicket-bootstrap3.version}</version>
</dependency>
```

Add the following maven dependency to your project `pom.xml` if you want to import components:

```xml
<dependency>
	<groupId>de.alpharogroup</groupId>
	<artifactId>wicket-component-expressions</artifactId>
	<version>${wicket-component-expressions.version}</version>
</dependency>
```

## Run Examples 

Example projects are moved to the [wicket-examples](https://github.com/astrapi69/wicket-examples) project.

You can run every example project by doing following steps. For instance if you want to start the examples for the project turbo-ninja-components you do this:

First change to the directory and build the project:
```bash
cd turbo-ninja-components-examples
mvn clean install
```
Running the example:
```bash
mvn jetty:run
```
Call then http://localhost:8080/

Another way is to start the example application programmaticly with jetty. In every example project there exists a start class with a main method that have the prefix Start**** and the suffix ****Examples. 
 
## Open Issues
[![Open Issues](https://img.shields.io/github/issues/astrapi69/turbo-ninja.svg?style=flat)](https://github.com/astrapi69/turbo-ninja/issues) 

## Documentation

  * [BaseWebApplication][BaseWebApplication]
  * [Counting online users with wicket][Counting online users with wicket]
  * [Internationalization with ResourceModelFactory][Internationalization with ResourceModelFactory]
  * [Wicket and I18n][Wicket and I18n]
  * [Replacing wicket panels with ajax][Replacing wicket panels with ajax]
  
  [Replacing wicket panels with ajax]: https://github.com/astrapi69/turbo-ninja/wiki/Replacing-wicket-panels-with-ajax "Replacing wicket panels with ajax"
  [Wicket and I18n]: https://github.com/astrapi69/turbo-ninja/wiki/Wicket-and-I18n "Wicket and I18n"  
  [Internationalization with ResourceModelFactory]: https://github.com/astrapi69/turbo-ninja/wiki/Internationalization-with-StringResourceModel-and-ResourceModelFactory "Internationalization with ResourceModelFactory"
  [Counting online users with wicket]: https://github.com/astrapi69/turbo-ninja/wiki/Counting-online-users-with-wicket "Counting online users with wicket"
   [BaseWebApplication]: https://github.com/astrapi69/turbo-ninja/wiki/Extending-from-BaseWebApplication "Extending from BaseWebApplication"

## Want to Help and improve it? ###

The source code of turbo-ninja are on GitHub. Please feel free to fork and send pull requests!

Create your own fork of [astrapi69/turbo-ninja/fork](https://github.com/astrapi69/turbo-ninja/fork)

To share your changes, [submit a pull request](https://github.com/astrapi69/turbo-ninja/pull/new/master).

Don't forget to add new units tests on your changes.

## Contacting the Developer

Do not hesitate to contact the turbo-ninja developers with your questions, concerns, comments, bug reports, or feature requests.

- Feature requests, questions and bug reports can be reported at the [issues page](https://github.com/astrapi69/turbo-ninja/issues).

## Note

No animals were harmed in the making of this library.

# Donate

If you like this library, please consider a donation through

<a href="http://flattr.com/thing/4067786/astrapi69turbo-ninja-on-GitHub" target="_blank">
<img src="http://api.flattr.com/button/flattr-badge-large.png" alt="Flattr this" title="Flattr this" border="0" />
</a>

