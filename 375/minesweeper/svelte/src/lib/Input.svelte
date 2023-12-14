<script lang="ts">
  export let placeholder = "Text";
  export let value = 0;
</script>

<div class="flex items-center m-1">
  <label class="w-48 text-overlay0 font-bold" for={placeholder}
    >{placeholder}</label
  >
  <input
    name={placeholder}
    class="font-bold outline-none bg-base border-overlay0 border-2 rounded-lg w-20 h-10 p-2 txt-lg focus:border-sky text-rosewater"
    bind:value
    type="number"
  />
</div>
