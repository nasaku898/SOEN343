const houseLayoutSample = {
    "rooms": [
      {
        "name": "salon",
        "doors": [
          {
            "type": "exterior",
            "position": "south",
            "open": true,
            "rooms": [
              "salon"
            ],
            "locked": true
          }
        ],
        "lights": [
          {
            "isLightOn": true
          },
          {
            "isLightOn": false
          }
        ],
        "houseWindows": [
          {
            "blocked": true,
            "open": true
          },
          {
            "blocked": false,
            "open": false
          }
        ]
      },
      {
        "name": "garage",
        "doors": [
          {
            "type": "interior",
            "position": "west",
            "open": false,
            "rooms": [
              "garage",
              "salon"
            ]
          }
        ],
        "lights": [
          {
            "isLightOn": true
          }
        ],
        "houseWindows": [
          {
            "blocked": true,
            "open": true
          }
        ]
      }
    ]
  }

  export default houseLayoutSample