#!/usr/bin/env python3

import os
import json
import datetime
import urllib.request

# Base output directory
OUTPUT_DIRECTORY = os.path.dirname(os.path.dirname(os.path.realpath(__file__))) + '/tv-guide'

# Primary & Secondary Genre ID Mappings
# http://confluence.cg.bskyb.com/display/API/sub-genre
GENRE_MAP = {
    0: 'Miscellaneous',
    1: 'Specialist',
    2: 'Kids',
    3: 'Entertainment',
    4: 'Music & Radio',
    5: 'News & Docs',
    6: 'Movies',
    7: 'Sports'
}

SUBGENRE_MAP = {
    0: {
       31: 'Free Preview',
       0: None 
    },
    1: {
        0: None,
        1: 'Adult',
        2: 'Event',
        3: 'Shopping',
        4: 'Gaming & Dating'
    },
    2: {
        1: 'Cartoons',
        2: 'Comedy',
        3: 'Drama',
        4: 'Educational',
        6: 'Factual',
        9: 'Games',
        8: 'Game Shows',
        7: 'Magazine',
        0: None,
        5: 'Under 5\'s'
    },
    3: {
        1: 'Action',
        8: 'Animation',
        22: 'Antiques',
        9: 'Chat Shows',
        2: 'Comedy',
        3: 'Detective',
        4: 'Drama',
        11: 'Factual',
        5: 'Game Shows',
        20: 'Medical',
        21: 'Reviews',
        6: 'Sci-fi',
        7: 'Soaps',
        15: 'Technology',
        16: 'Arts',
        24: 'Art & Lit',
        25: 'Ballet',
        10: 'Cooking',
        12: 'Fashion',
        13: 'Gardening',
        18: 'Home',
        17: 'Lifestyle',
        19: 'Magazine',
        23: 'Motors',
        26: 'Opera',
        14: 'Travel',
        0: None
    },
    4: {
        7: 'Alternative',
        17: 'Art & Lit',
        13: 'Ballet',
        27: 'Business',
        1: 'Classical',
        9: 'Club & Dance',
        20: 'Comedy',
        15: 'Current Affairs',
        12: 'Dance',
        24: 'Discussion',
        19: 'Drama',
        23: 'Easy',
        25: 'Entertainment',
        8: 'Events',
        18: 'Factual',
        16: 'Features',
        2: 'Folk/Country',
        29: 'Gold',
        10: 'Hip Hop',
        4: 'Jazz',
        14: 'Kids',
        21: 'Lifestyle',
        3: 'National',
        22: 'News',
        5: 'Opera',
        26: 'Religious',
        6: 'Rock & Pop',
        28: 'Science',
        30: 'Soap',
        11: 'Soul/R&B',
        31: 'Sport',
        0: None
    },
    5: {
        1: 'Business',
        19: 'Features',
        8: 'News',
        7: 'Politics',
        20: 'Showbiz',
        22: 'Transport',
        0: None,
        18: 'World Affairs',
        3: 'Adventure',
        15: 'Ancient',
        4: 'Biography',
        2: 'Cultures',
        17: 'Docudrama',
        6: 'Features',
        14: 'Historical',
        5: 'Learning',
        9: 'Nature',
        21: 'Politics',
        10: 'Religious',
        11: 'Science',
        12: 'Showbiz',
        16: 'Transport',
        13: 'War',
        23: 'World Affairs'
    },
    6: {
        1: 'Action',
        18: 'Adventure',
        2: 'Animation',
        4: 'Comedy',
        6: 'Drama',
        17: 'Erotic',
        15: 'Factual',
        5: 'Family',
        16: 'Fantasy',
        10: 'Horror',
        12: 'Musical',
        13: 'Mystery',
        11: 'Romance',
        8: 'Sci-fi',
        9: 'Thriller',
        0: None,
        19: 'War',
        14: 'Western'
    },
    7: {
        2: 'Athletics',
        3: 'Baseball',
        4: 'Basketball',
        5: 'Boxing',
        6: 'Cricket',
        19: 'Darts',
        14: 'Equestrian',
        21: 'Extreme',
        7: 'Fishing',
        8: 'Football',
        9: 'Golf',
        10: 'Ice Hockey',
        11: 'Motor Sport',
        12: 'Racing',
        13: 'Rugby',
        16: 'Snooker',
        17: 'Tennis',
        0: None,
        1: 'US Football',
        20: 'Watersports',
        15: 'Wintersports',
        18: 'Wrestling'
    }
}

# Genre IDs for Channels
# http://confluence.cg.bskyb.com/display/API/genre+id
CHANNEL_GENRE_MAP = {
    11: 'All Channels',
    12: 'Entertainment',
    13: 'Lifestyle & Culture',
    14: 'Movies',
    15: 'Sports',
    16: 'News',
    17: 'Documentaries',
    18: 'Kids',
    19: 'Music',
    20: 'Radio',
    21: 'Shopping',
    22: 'Religion',
    23: 'International',
    24: 'Gaming & Dating',
    25: 'Specialist',
    26: 'Adult'
}

def fetch_hawk_services(bouquet="4101", subbouquet="1"):
    # Perform a HTTP request for the channels
    service_url = "https://awk.epgsky.com/hawk/linear/services/{}/{}".format(bouquet, subbouquet)
    service_request = urllib.request.Request(service_url)

    # Obtain the services from hawk
    service_response = urllib.request.urlopen(service_request).read()
    services = json.loads(service_response.decode('utf-8'))['services']

    # Obtain additional channel information
    channel_url = "http://epgservices.sky.com/98.0.0/api/2.1/region/json/{}/{}".format(bouquet, subbouquet)
    channel_response = urllib.request.urlopen(channel_url).read()
    channel_body = json.loads(channel_response.decode('utf-8'))['init']['channels']

    # Merge the channel information into the services
    for channel in channel_body:
        sid = str(channel['c'][0])

        for index, service in enumerate(services):
            if service['sid'] == sid:
                services[index]['pt'] = channel.get('pt', None)
                services[index]['subgenre'] = CHANNEL_GENRE_MAP[channel['c'][2]]
                break

    return services

def fetch_hawk_schedule(date, sid):
    # Perform a HTTP request for the schedule
    url = "https://awk.epgsky.com/hawk/linear/schedule/{}/{}".format(date.strftime("%Y%m%d"), sid)
    request = urllib.request.Request(url)

    # return the response as a json object
    response = urllib.request.urlopen(request).read()
    return json.loads(response.decode('utf-8'))

def fetch_channels_list(bouquet="4101", subbouquet="1"):
    channels = []
    services = fetch_hawk_services(bouquet, subbouquet)

    for service in services:
        if service["adult"] == False and int(service['c']) < 1000:
            channel = {
                "sid": service['sid'],
                "t": service['t'],
                "c": service['c'],
                "sf": service['sf'],
                "sg": service.get('subgenre', None)
            }

            # Remove keys that have a null or empty value
            for key, value in channel.copy().items():
                if value == None or str(value) == '':  
                    del channel[key]

            channels.append(channel)
    
    return channels

def fetch_channel_schedule(date, sid):
    # Fetch the schedule from hawk
    hawk_schedule = fetch_hawk_schedule(date, sid)

    # Create a simpler schedule object
    schedule = {
        "date": hawk_schedule["date"],
        "sid": hawk_schedule["schedule"][0]["sid"],
        "events": []
    }

    for hawk_event in hawk_schedule["schedule"][0]["events"]:
        # Build a simpler event object
        event = {
            "eid": hawk_event.get("eid", None),
            "st": hawk_event.get("st", None),
            "d": hawk_event.get("d", None),
            "t": hawk_event.get("t", None),
            "sy": hawk_event.get("sy", None),
            "eg": GENRE_MAP[hawk_event.get("eg", 0)],
            "esg": SUBGENRE_MAP[hawk_event.get("eg", 0)][hawk_event.get("esg", 0)],
            "seasonnumber": hawk_event.get("seasonnumber", None),
            "episodenumber": hawk_event.get("episodenumber", None),
            "programmeuuid": hawk_event.get("programmeuuid", None),
            "seasonuuid": hawk_event.get("seasonuuid", None),
            "seriesuuid": hawk_event.get("seriesuuid", None),
            "r": hawk_event.get("r", None),
            "s": hawk_event.get("s", False),
            "ad": hawk_event.get("ad", False),
            "hd": hawk_event.get("hd", False),
            "new": hawk_event.get("new", False)
        }

        # Remove keys that have a null or empty value
        for key, value in event.copy().items():
            if value == None or str(value) == '':  
                del event[key]

        schedule["events"].append(event)
        
    return schedule

def mkdir(path):
    directory = os.path.dirname(path)
    if not os.path.exists(directory):
        os.makedirs(directory)

# Writes the channels list
def write_channels(channels):
    path = "{0}/services.json".format(OUTPUT_DIRECTORY)
    mkdir(path)

    with open(path, 'w') as f:
        json.dump(channels, f, indent=4)

# Writes the channel schedule
def write_channel_schedules(channels, days=7):
    for channel in channels:
        for i in range(0, days):
            date = datetime.date.today() + datetime.timedelta(days=i)
            schedule = fetch_channel_schedule(date, channel["sid"])
            write_channel_schedule(schedule)

# Writes the channel schedule
def write_channel_schedule(schedule):
    path = "{0}/schedule/{1}/{2}.json".format(OUTPUT_DIRECTORY, schedule['date'], schedule['sid'])
    mkdir(path)
    
    with open(path, 'w') as f:
        json.dump(schedule, f, indent=4)


if __name__ == "__main__":
    # Fetch the current channel list
    channels = fetch_channels_list()
    # Write out a channels.json file
    print("Writing channels list... ", end = '')
    write_channels(channels)
    print("done. ")

    # Write out all channel schedules
    print("Writing channel schedules... ")
    write_channel_schedules(channels)
    print("done. ")
