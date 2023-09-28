<script lang="ts">
    import StationMap from "./StationMap.svelte";

	export let visible: boolean;
</script>

{#if visible}
  <StationMap />
{/if}
