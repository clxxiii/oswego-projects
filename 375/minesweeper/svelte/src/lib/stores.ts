import { writable } from "svelte/store";
import * as example from "./example.json";
import type { CellContent } from "./CellContent";

export let grid = writable<CellContent[][]>(example.grid);
