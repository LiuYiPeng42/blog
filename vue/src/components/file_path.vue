<template>
    <el-affix style="margin-top: -10px;" :style="{ width: this.menu_width + 'px' }">
        <el-menu :style="{ width: this.menu_width + 'px' }" background-color="#F5F5F5" mode="horizontal"
            :collapse="isCollapse">
            <template v-for="item in menu_data">
                <el-sub-menu class="name" :title="item.name" :index="item.name" v-if="item.children.length > 0">
                    <template #title>
                        <el-icon>
                            <Folder />
                        </el-icon>
                        <span class="name" :title="item.name">{{ item.name }}</span>
                    </template>
                    <file_selector @file_path="get_file_path" :path_data="item.children"></file_selector>
                </el-sub-menu>
            </template>
        </el-menu>
    </el-affix>
</template>

<script>
import file_selector from "./file_selector.vue"


export default {
    data() {
        return {
            menu_data: [],
        }
    },
    props: {
        menu_width: {
            type: Number,
            default: 200
        },
        menu_height: {
            type: Number,
            default: 1080
        },
        isCollapse: {
            type: Boolean,
            default: false
        }
    },
    components: {
        file_selector
    },
    watch: {
        // window_width(){
        // 	console.log(this.window_width)
        // }
    },
    mounted() {
        this.get_dir_tree()
    },

    methods: {
        get_dir_tree() {
            this.$axios.get('http://localhost:8081/path/get', {
            }).then((result) => {
                this.menu_data = result.data.children
                console.log(this.menu_data)
            })
        },
        get_file_path(path) {
            this.$emit('file_path', path)
        },

    }
}

</script>

<style></style>