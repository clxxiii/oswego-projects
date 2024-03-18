import { writable } from "svelte/store";
import * as example from "./example.json";
import type { CellContent } from "./CellContent";

type Grid = {
  height: number;
  width: number;
  bombs: number;
  flags: number;
  progress: number;
  solved: boolean;
  failed: boolean;
  grid: CellContent[][];
}

export let grid = writable<Grid>(example);
