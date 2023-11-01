<template>
  <announcement-create :is-edit="true" />
</template>

<script>
import AnnouncementCreate from "@/views/announcement/components/AnnouncementCreate.vue";
export default {
  name: 'EditForm',
  components: {AnnouncementCreate }
}
</script>

