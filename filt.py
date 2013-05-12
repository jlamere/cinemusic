import sys
import json

outlist = []

for line in sys.stdin:
    artist, soundtrack, id = line.strip().split('<sep>')
    dict = {
        'artist' : artist,
        'soundtrack' : soundtrack,
        'id': id
    }

    outlist.append(dict)


print json.dumps(outlist)


