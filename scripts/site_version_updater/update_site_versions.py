import requests
import os
import json

CF_MR_TO_GAME_VERSIONS_DICT = {
    # ( Project name, CF Project ID, CF File ID, MR File ID )

    ('Friends and Foes Compat Datapack', '592278', 4994554, 'D8u1J7l4'): ['1.20.2'],
    ('All Bark All Bite Compat Datapack', '906468', 4994465, 'l7GgSqoh'): ['1.20.2'],

    ('Structure Void Toggle NeoForge', '357304', 4888921, '79xRp4Rg'): ['1.20.2', '1.20.3', '1.20.4'],
    ('Structure Void Toggle Fabric', '357304', 4736611, 'te56DL5J'): ['1.20.2', '1.20.3', '1.20.4'],

    ('CommandStructures NeoForge', '565119', 4934806, 'A7g4ZyFr'): ['1.20.3', '1.20.4'],
    ('CommandStructures Fabric', '565119', 4934805, 'emSRuXFF'): ['1.20.3', '1.20.4'],

    ('WITS NeoForge', '909375', 4934724, 'yQrQ3xiT'): ['1.20.3', '1.20.4'],
    ('WITS Fabric', '909375', 4934723, '6Xjvbk9m'): ['1.20.3', '1.20.4'],

    ('FullStack Watchdog NeoForge', '849817', 4888889, 'llivrfql'): ['1.20.2', '1.20.3', '1.20.4'],
    ('FullStack Watchdog Fabric', '849817', 4488547, 'bQS4RMW3'): ['1.17.1', '1.18', '1.18.1', '1.18.2', '1.19', '1.19.1', '1.19.2', 
                                                                   '1.19.3', '1.19.4', '1.20', '1.20.1', '1.20.2', '1.20.3', '1.20.4'],
}

MR_AUTH_KEY = os.getenv('MODRINTH')
MR_PATCH_ENDPOINT = 'https://api.modrinth.com/v2/version/$1'
MR_PATCH_HEADERS = {'content-type': 'application/json', 'Authorization': MR_AUTH_KEY}

CF_AUTH_KEY = os.getenv('CURSEFORGEKEY')
CF_POST_ENDPOINT = 'https://minecraft.curseforge.com/api/projects/$1/update-file'
CF_GET_HEADERS = {'X-Api-Token': CF_AUTH_KEY}
CF_POST_HEADERS = {'Accept': 'application/json', 'X-Api-Token': CF_AUTH_KEY}

# Have to call 2 CF APIs just to get the correct ID for a MC game version.
CF_GAME_VERSION_TYPES_ENDPOINT = 'https://minecraft.curseforge.com/api/game/version-types?cache=true'
CF_GAME_VERSION_ENDPOINT = 'https://minecraft.curseforge.com/api/game/versions?cache=true'
CfGameVersionTypesResponse = requests.get(url=CF_GAME_VERSION_TYPES_ENDPOINT, headers=CF_GET_HEADERS).json()
CfGameVersionResponse = requests.get(url=CF_GAME_VERSION_ENDPOINT, headers=CF_GET_HEADERS).json()
CfMcVersions = [gv for gv in CfGameVersionResponse if any(gv['gameVersionTypeID'] == gvt['id'] for gvt in CfGameVersionTypesResponse)]
CFMcVersionsToIds = {}
for McGameVersion in CfMcVersions:
    CFMcVersionsToIds[McGameVersion['name']]=McGameVersion['id']

# Begin the file updating
for fileVersions, gameVersions in CF_MR_TO_GAME_VERSIONS_DICT.items():

    # Curseforge file modification
    CfURL = CF_POST_ENDPOINT.replace("$1", fileVersions[1])
    CfData = {
        "fileID": fileVersions[2],
        "gameVersions": [CFMcVersionsToIds[gv] for gv in gameVersions]
    }
    CfJsonData = json.dumps(CfData)
    CfResponse = requests.post(url=CfURL, headers=CF_POST_HEADERS, files={'metadata': (None, CfJsonData)})
    print(f"{fileVersions[0]} CurseForge response is: {CfResponse.status_code} - {CfResponse.text} - Data: {CfJsonData}")
    if CfResponse.status_code != 200:
        input("Error in CF response. Click Enter to continue program.")

    # Modrinth file modification
    MrURL = MR_PATCH_ENDPOINT.replace("$1", fileVersions[3])
    MrData = {
        "game_versions": gameVersions
    }
    CfJsonData = json.dumps(MrData)
    MrResponse = requests.patch(url=MrURL, headers=MR_PATCH_HEADERS, data=CfJsonData)
    print(f"{fileVersions[0]} Modrinth response is: {MrResponse.status_code} - {MrResponse.text} - Data: {CfJsonData}")
    if MrResponse.status_code != 200:
        input("Error in CF response. Click Enter to continue program.")


