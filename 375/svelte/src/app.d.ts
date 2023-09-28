export type StationSlot = {
  x: number;
  y: number;
  station: Station | null;
};

export type Station = {
  service: {
    a: boolean;
    b: boolean;
    c: boolean;
    d: boolean;
  };
  id: number;
};
