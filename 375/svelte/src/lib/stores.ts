import { writable } from "svelte/store";
import type { StationSlot } from "../app";
import * as example from "./example.json";

export let map = writable<StationSlot[]>(example.map);
