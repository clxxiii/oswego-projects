import { writable } from "svelte/store";
import * as example from "./example.json";

export let grid = writable<number[][]>(example.grid);
