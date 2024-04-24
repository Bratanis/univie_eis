---
title: Architecture
type: description
---
A microservice architecture is used to realize the functionalities for geocoding.

#### Service Operations

As a case, geocoding has been selected. The operations provided allow for 

- forward geocoding (location name to coordinates, [Definition](https://www.techopedia.com/definition/12809/geocoding)), and 
- reverse geocoding (coordinates to location name, [Definition](https://en.wikipedia.org/wiki/Reverse_geocoding)).

For the online service, the API provided by [Nominatim](https://nominatim.org/) has been selected and wrapped within OLIVE (configuration available for [download](https://gitlab.dke.univie.ac.at/eis/2021s/group0/-/blob/master/msc_config/b3613704-a884-4c12-9cf0-574b5332a76f.json)).


For the offline case, a grid-based implementation is provided, that uses k-d-algorithms (inspired by [phishman3579/java-algorithms-implementation](https://github.com/phishman3579/java-algorithms-implementation/blob/master/src/com/jwetherell/algorithms/data_structures/KdTree.java)for closness determination and basic visualisation [download](https://gitlab.dke.univie.ac.at/eis/2021s/group0/-/blob/master/msc_config/7093b102-e1c9-4c7b-bb1d-6a0b824f7a5b.json)

##### Input:

The input messages required are:

- forward geocoding (online): search string, in different formats
- reverse geocoding (online): longitude and latitude as double
- reverse geocoding (offline): grid size as an integer and search point as x and y in double format

##### Operations:

The following operations are provided for the online use, based on Nominamtims service capabilities.

- geocode_online_freeform: free-search
- geocode_online_city: search by city names
- geocode_online_postalcode: search by ZIP code
- geocode_online_country: search by country name
- reverse_geocode_online: search by long/lat coordinates

##### Output:

The output messages/results produced are:

- forward geocoding (online): longitude and latitude value of the first found element (in case more nodes are found, these are disregarded, in JSON format)
- reverse geocoding (online): location and display name as strings (in JSON format)
- reverse geocoding (offline): closest nodes in grid based on a fixed radius of k=1, in JSON format
- reverse geocoding (offline), display: a PNG, based64 encoded visualizing the search performed


