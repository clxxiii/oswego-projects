<script lang="ts">
  import type { CellContent } from "../CellContent";
  import { Bomb, Flag } from "lucide-svelte";
  import { sendFlagMessage, sendRevealMessage } from "../websocket";
  export let data: CellContent;

  let cellElement: HTMLTableCellElement;

  const colors: { [key: number]: string } = {
    0: "var(--ctp-base)",
    1: "var(--ctp-blue)",
    2: "var(--ctp-green)",
    3: "var(--ctp-red)",
    4: "var(--ctp-mauve)",
    5: "var(--ctp-yellow)",
    6: "var(--ctp-sky)",
    7: "var(--ctp-flamingo)",
    8: "var(--ctp-pink)",
  };

  let text = "var(--ctp-surface2)";
  let bg = "var(--ctp-surface2)";
  if (data.value) {
    bg = colors[data.value];
  } else if (data.bomb) {
    text = "var(--ctp-red)";
  }

  const left = (e: MouseEvent) => {
    console.log("left");
    if (data.flagged) return;

    const [x, y] = getCellCoordinates();
    sendRevealMessage(x, y);
  };
  const right = (e: MouseEvent) => {
    e.preventDefault();
    console.log("right");

    const [x, y] = getCellCoordinates();
    sendFlagMessage(x, y);
  };

  // I LOVE DOM MANIPULATION I LOVE DOM MANIPULATION
  const getCellCoordinates = (): number[] => {
    const row = cellElement.parentElement;
    if (!row) return [];
    const table = row.parentElement;
    if (!table) return [];

    const rowIndex = Array.from(row.children).indexOf(cellElement);
    const tableIndex = Array.from(table.children).indexOf(row);

    return [rowIndex, tableIndex];
  };
</script>

<td bind:this={cellElement}>
  {#if data.revealed}
    <div
      class="revealed w-8 h-8 rounded items-center justify-center flex font-extrabold"
      style="--bg: {bg}; --color: {text}"
    >
      {#if data.value}
        {data.value}
      {:else if data.bomb}
        <Bomb />
      {/if}
    </div>
  {:else}
    <button
      class="w-8 h-8 rounded items-center justify-center flex bg-base hover:bg-surface1 transition text-text cursor-pointer"
      data-flagged={data.flagged}
      on:click={left}
      on:contextmenu={right}
    >
      {#if data.flagged}
        <Flag />
      {/if}
    </button>
  {/if}
</td>

<style>
  div,
  button {
    margin: 0.1rem;
  }
  .revealed {
    color: rgba(var(--color));
    background-color: rgba(var(--bg));
  }
</style>
