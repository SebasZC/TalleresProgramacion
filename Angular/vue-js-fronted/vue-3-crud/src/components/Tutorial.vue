<template>
  <div v-if="currentTutorial" class="edit-form">
    <h4>Empleado</h4>
    <form>
      <div class="form-group">
        <label for="title">Nombre</label>
        <input type="text" class="form-control" id="title"
          v-model="currentTutorial.title"
        />
      </div>
      <div class="form-group">
        <label for="description">Apellido</label>
        <input type="text" class="form-control" id="description"
          v-model="currentTutorial.description"
        />
      </div>
      <div class="form-group">
        <label><strong>Estado:</strong></label>
        {{ currentTutorial.published ? "Activo" : "Inactivo" }}
      </div>
    </form>
    <button class="badge badge-primary mr-2"
      v-if="currentTutorial.published"
      @click="updatePublished(false)"
    >
      Inhabilitar
    </button>
    <button v-else class="badge badge-primary mr-2"
      @click="updatePublished(true)"
    >
      Habilitar
    </button>
    <button class="badge badge-danger mr-2"
      @click="deleteTutorial"
    >
      Eliminar
    </button>
    <button type="submit" class="badge badge-success"
      @click="updateTutorial"
    >
      Actualizar
    </button>
    <p>{{ message }}</p>
  </div>
  <div v-else>
    <br />
    <p>Sebastian Zapata</p>
  </div>
</template>
<script>
import TutorialDataService from "../services/TutorialDataService";
export default {
  name: "tutorial",
  data() {
    return {
      currentTutorial: null,
      message: ''
    };
  },
  methods: {
    getTutorial(id) {
      TutorialDataService.get(id)
        .then(response => {
          this.currentTutorial = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    updatePublished(status) {
      var data = {
        id: this.currentTutorial.id,
        title: this.currentTutorial.title,
        description: this.currentTutorial.description,
        published: status
      };
      TutorialDataService.update(this.currentTutorial.id, data)
        .then(response => {
          console.log(response.data);
          this.currentTutorial.published = status;
          this.message = 'Estado actualizado exitosamente';
        })
        .catch(e => {
          console.log(e);
        });
    },
    updateTutorial() {
      TutorialDataService.update(this.currentTutorial.id, this.currentTutorial)
        .then(response => {
          console.log(response.data);
          this.message = 'Empleado actualizado exitosamente!';
        })
        .catch(e => {
          console.log(e);
        });
    },
    deleteTutorial() {
      TutorialDataService.delete(this.currentTutorial.id)
        .then(response => {
          console.log(response.data);
          this.$router.push({ name: "tutorials" });
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.message = '';
    this.getTutorial(this.$route.params.id);
  }
};
</script>
<style>
.edit-form {
  max-width: 300px;
  margin: auto;
}
</style>