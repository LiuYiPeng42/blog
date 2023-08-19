<template>
    <div>
        <ul>
            <li v-for="item of toc" :key="item.id" @click="toTarget(item.id)"
                :style="{ 'padding-left': item.tag - maxTitle + 'em', 'font-size': (maxTitle - item.tag) * 2 + 25 + 'px', 'color': '#8599ad' }"
                v-html="item.text">
            </li>
        </ul>

        <article class="markdown-body" v-html="this.md_html"></article>

    </div>
</template>


<script>
import { marked } from "marked";
import hljs from "highlight.js"; // 引入 highlight.js
import "highlight.js/styles/github.css"; // 引入高亮样式 这里我用的是sublime样式



export default {
    data() {
        return {
            markdown: "",
            md_html: "",
            toc: [],
            maxTitle: 5,
        }
    },
    props: ['blog_url'],
    async mounted() {
        this.create_renderer()

        this.render_markdown('http://localhost:8081/md/get?path=markdown/MainPage.md')

    },
    watch: {
        async blog_url() {
            this.render_markdown(this.blog_url)
        }
    },
    methods: {
        create_renderer() {
            let that = this
            let anchor = 0;

            const renderer = {
                code(codes, infostring, escaped) {
                    // 验证当前语言是否存在
                    const language = hljs.getLanguage(infostring) ? infostring : 'plaintext';
                    // 使代码转换成带有标签的节点
                    const textHtml = hljs.highlight(codes, { language }).value;

                    let lines = [];
                    if (textHtml.length === 0) {
                    } else {
                        lines = textHtml.split(/\r\n|\r|\n/g);
                    }

                    // 自定义代码块的标签节点
                    let html = '';
                    for (var i = 0, l = lines.length; i < l; i++) {
                        let text = lines[i].length > 0 ? lines[i] : ' '
                        html += `<tr>
		<td class="blob-num" data-line-number="${i}"></td>
		<td class="blob-code blob-code-inner">${text}</td>
	  </tr>`;
                    }

                    return `<table class="code">${html}</table>`;
                },
                heading(text, level, raw) {
                    //保存这篇文章的最大标签 
                    if (level < that.maxTitle) {
                        that.maxTitle = level
                    }
                    anchor += 1
                    /* 
                    toc:数组用于保存标题，
                    id:标题id,用于点击目录滚动到改标题
                    tag:记录属于那个标签（h1……h6）
                    test:标签内容
                    */
                    // console.log(level)
                    that.toc.push(
                        {
                            'id': anchor,
                            'tag': level,
                            'text': text
                        }
                    )
                    return `<h${level} id="toc-nav${anchor}">${text}</h${level}>`;
                },
                link(href, title, text) {
                    return '<a href="' + href + '" title="' + text + '" target="_blank">' + text + '</a>';
                },
                image(href, title, text) {
                    // <img src="Redis命令与数据类型/1.png" alt="1" style="zoom:80%;">
                    console.log(href, title, text)
                    // console.log(that.maxTitle)
                    // this.blog_url.slice(0, -3) + href.split("/")[-1]
                    console.log(that.blog_url)
                    return '<img src="http://localhost:8082/' + that.blog_url.split('=')[1].slice(0, -3) + "/" + href.split("/")[1] + '" ' + 'alt="' + text + '">'

                }

            };

            marked.use({ renderer });

        },
        render_markdown(url) {
            this.toc = []
            this.$axios.get(url, {
            }).then((result) => {
                this.markdown = result.data
                this.md_html = marked.parse(this.markdown)
                // this.md_html = this.markdown
                this.render_formula()
            })
        },
        render_formula(elements) {
            if (!window.MathJax) {
                return;
            }
            window.MathJax.startup.promise = window.MathJax.startup.promise
                .then(() => {
                    return window.MathJax.typesetPromise(elements);
                })
                .catch((err) => console.log("Typeset failed: " + err.message));
            return window.MathJax.startup.promise;
        },
        toTarget(target) {
            target = '#toc-nav' + target
            let toElement = document.querySelector(target);
            toElement.scrollIntoView({
                behavior: 'smooth',
                block: 'center',
                inline: 'nearest'
            })
        },

    }
}

</script>


<style lang="scss">
table.code {
    border-spacing: 0;
    background-color: #e7e7e7;
    border-collapse: collapse;
    font-family: ui-monospace, SFMono-Regular, SF Mono, Menlo, Consolas,
        Liberation Mono, monospace;
}

.hljs-string {
    // width: 100% !important;
    // float: left !important;
    // overflow: hidden !important;
    // text-overflow: ellipsis !important;
    white-space: normal !important;
}

.blob-num {
    position: relative;
    width: 1%;
    min-width: 30px;
    padding-right: 10px;
    padding-left: 5px;
    font-size: 12px;
    line-height: 20px;
    color: #6e7781;
    text-align: right;
    white-space: nowrap;
    vertical-align: top;
    -webkit-user-select: none;
    user-select: none;
}

.blob-num::before {
    content: attr(data-line-number);
}

.blob-code-inner {
    display: table-cell;
    overflow: visible;
    font-size: 12px;
    color: #24292f;
    word-wrap: anywhere;
    white-space: pre;
}

.blob-code {
    position: relative;
    padding-right: 10px;
    padding-left: 10px;
    line-height: 20px;
    vertical-align: top;
}
</style>