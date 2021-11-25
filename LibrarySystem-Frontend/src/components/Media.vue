     <template> 
     <div>
        <table>
        <tr v-for="media in medias" :key="media.name">
          <td>
            {{ media.name }}
          </td>
        </tr>
      </table>
     </div>
      </template>
      <script>
      </script>
      <style>
      </style>
      