
<template>
	<div style="background-color: (250, 250, 250);"
		:style="{ width: this.menu_width + 'px', height: this.menu_height + 'px' }">
		<file_path :menu_width="this.window_width" class="menu" @file_path="get_file_path">
		</file_path>

		<blog :blog_url="blog_url" class="blog">
		</blog>

	</div>
</template>


<script >
import blog from './components/blog.vue'
import file_path from './components/file_path.vue'


export default {
	data() {
		return {
			blog_url: '',
			window_width: document.documentElement.clientWidth, //实时屏幕宽度
			window_height: document.documentElement.clientHeight, //实时屏幕宽度

			isCollapse: false
		}
	},
	mounted() {
		// 实时获取浏览器宽度高度
		let that = this;
		window.onresize = () => {
			return (() => {
				window.fullWidth = document.documentElement.clientWidth;
				window.fullHeight = document.documentElement.clientHeight;
				that.window_width = window.fullWidth; // 宽
				that.window_height = window.fullHeight; // 宽
			})()
		};
	},
	watch: {
		window_width() {
			console.log(this.window_height)
		}
	},
	components: {
		blog, file_path
	},
	methods: {
		get_file_path(path) {
			// console.log(path)
			this.blog_url = 'http://localhost:8081/md/get?path=' + path
		},

	}

}

</script>

<style>
.menu {
	margin-bottom: 100px;
}

.blog {
	width: 800px;
	margin: 0 auto;
	left: 0;
	right: 0;
}
</style>

