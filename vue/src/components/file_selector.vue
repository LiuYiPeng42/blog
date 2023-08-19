<template>
    <div>
        <template v-for="item in path_data">
            <el-sub-menu class="name" :title="item.name" :index="item.name" v-if="item.children.length > 0">
                <template #title>
                    <!-- <el-icon><Folder /></el-icon> -->
                    <span class="name" :title="item.name" @click="get_path(item.path)">{{ item.name }}</span>
                </template>
                <file_selector @file_path="get_path" :path_data="item.children"></file_selector>
            </el-sub-menu>
            <el-menu-item class="name" :title="item.name" v-else :index="item.name">
                <el-icon><Document /></el-icon>
                <template #title>
                    <span class="name" :title="item.name" @click="get_path(item.path)">{{ item.name }}</span>
                </template>
            </el-menu-item>
        </template>
    </div>
</template>

<script>

export default {

    data() {
        return {
            // data: []
        }
    },

    props: {
        path_data: {
            type: Array,
            default: []
        },
        get_file_path: {
            type: Function
        }
    },

    methods: {
        get_path(path) {

            this.$emit('file_path',path)
        }
    }

};
</script>


<style>
.name {
    overflow: hidden;
    text-overflow: ellipsis;
    font-size: 15px;
}
</style>