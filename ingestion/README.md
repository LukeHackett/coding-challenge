# TV Schedule Ingestion

The overall objective of this assessment is to build a working TV schedule ingestion service.

You are not expected to develop your own data source - we will provide you with an electronic programme guide API to develop against (see API docs below).

## EPG Services

To aid development of your solution, a snapshot of Sky's TV scheduling service has been provided.

A full list of Linear UK Services (often referred to as channels) can be obtained by making a `GET` request to the following endpoint:

`https://cdn.skyq.sky.com/recruitment/ingestion/tvguide/services.json`

The response will contain a list of services, to which can be used to build a service (or channel) listing. 

A service will contain the following fields:

| Field | Description                                              | Optional |
|:-----:|:---------------------------------------------------------|:--------:|
|  sid  | 	A unique identifier for the service                     |    No    |
|   c   | 	Channel number used by the EPG                          |    No    |
|   t   | 	Channel title used by the EPG                           |    No    |
|  sf   | 	Service format – one of `SD`, `HD`, `4K`, `AU` or `RA`	|    No    |
|  sg   | 	Service genre	                                        |    Yes   |

## Service Schedule

Each service has its own schedule for a given day. In order to obtain a service’s schedule, your application would need to perform a `GET` request to the following endpoint:

`https://cdn.skyq.sky.com/recruitment/ingestion/tvguide/schedule/<DATE>/<SID>.json`

Ensuring to replace SID with the service identifier value and a supported date value. 

The following dates are supported:
- 20240620
- 20240621
- 20240622
- 20240623
- 20240624
- 20240625
- 20240626

Example:

`https://cdn.skyq.sky.com/recruitment/ingestion/tvguide/schedule/20240620/3000.json`

The response will contain a list of linear events that can be used to build a service’s listings for a given day. 

An event will contain the following fields (some fields are optional):

|     Field     | Description                                                                                               | Optional |
|:-------------:|:----------------------------------------------------------------------------------------------------------|:--------:|
|     eid       | A unique identifier for the event                                                                         |    No    |
|      st       | The start time of the event as an epoch value                                                             |    No    | 
|       d       | The duration of the event in seconds                                                                      |    No    | 
|       t       | The title of the event                                                                                    |    No    | 
|      sy       | The synopsis of the event                                                                                 |    No    | 
|      eg       | The event genre                                                                                           |    No    | 
|      esg      | The event subgenre                                                                                        |   Yes    | 
| seasonnumber  | The season number within a series, with the first season being 1                                          |   Yes    | 
| episodenumber | The number of the episode within a series, with the first episode being 1                                 |   Yes    | 
| programmeuuid | A unique identifier for the programme (e.g. the fourth episode, of the second series, of Game of Thrones) |   Yes    | 
|  seriesuuid   | A unique identifier of the series (e.g. Game of Thrones)                                                  |   Yes    | 
|  seasonuuid   | A unique identifier of the season (e.g. the second season of Game of Thrones)                             |   Yes    | 
|       r       | The rating of the event (one of "U", "PG", "12", "15", "18")                                              |   Yes    | 
|       s       | Whether or not subtitles are supported                                                                    |    No    | 
|      ad       | Whether or not the event is audio described                                                               |    No    | 
|      hd       | Whether or not the event is broadcast in HD                                                               |    No    | 
|      new      | Whether or not the event is a "new" airing                                                                |    No    | 

