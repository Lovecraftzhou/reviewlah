<template>
  <announcement-create :is-edit="false" />
</template>

<script>
import AnnouncementCreate from "@/views/announcement/components/AnnouncementCreate.vue";

export default {
  name: 'CreateArticle',
  components: {AnnouncementCreate}
}
</script>

