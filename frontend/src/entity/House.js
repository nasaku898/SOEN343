export const House = {
  rooms: [
    {
      name: "salon",
      doors: [
        {
          type: "exterior",
          open: false,
          locked: true,
        },
      ],
      lights: [
        {
          isLightOn: true,
        },
        {
          isLightOn: false,
        },
      ],
      houseWindows: [
        {
          blocked: true,
          open: false,
        },
        {
          blocked: false,
          open: false,
        },
      ],
    },
    {
      name: "garage",
      doors: [
        {
          type: "interior",
          open: false,
        },
      ],
      lights: [
        {
          isLightOn: true,
        },
      ],
      houseWindows: [
        {
          blocked: true,
          open: false,
        },
      ],
      parents: [
        {
          id: 8,
        },
      ],
      city: "Montreal",
    },
  ],
};
