<template>
    <div>
        <div style="min-height: 810px; justify-content: center;position: relative" id="map" />

        <el-dialog v-model="dialogVisible" title="添加实验室" width="500">

            <el-form ref="addFormRef" style="max-width: 600px" :model="addForm" :rules="rules" label-width="auto"
                class="demo-ruleForm" :size="formSize" status-icon>
                <el-form-item label="实验室名称" prop="title">
                    <el-input v-model="addForm.title" placeholder="实验室名称" />
                </el-form-item>
                <el-form-item label="容纳人数" prop="capacity">
                    <el-input v-model="addForm.capacity" placeholder="容纳人数" type="number" />
                </el-form-item>
                <el-form-item label="实验室类型" prop="lab_type">
                    <el-select v-model="addForm.lab_type" placeholder="请选择实验室类型" size="large" style="width: 240px">
                        <el-option v-for="item in LabType" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </el-form-item>
                <el-form-item label="所属学院" prop="college_type">
                    <el-select v-model="addForm.college_type" placeholder="请选择所属学院" size="large" style="width: 240px">
                        <el-option v-for="item in CollegeType" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </el-form-item>
            </el-form>

            <template #footer>
                <div class="dialog-footer">
                    <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="handleAddConfirm()">
                        确定
                    </el-button>
                </div>
            </template>
        </el-dialog>
    </div>
</template>

<script setup>
import { Scene, ImageLayer, PointLayer, Popup } from '@antv/l7';
import { Map } from '@antv/l7-maps';
import { onMounted, ref, reactive } from 'vue';
import request from '../../util/request';
import {LabType, CollegeType} from '../../util/type'
// 同样你也可以初始化一个 Mapbox 地图

let scene
let popup

onMounted(() => {
    scene = new Scene({
        id: 'map',
        map: new Map({
            center: [500, 500],
            zoom: 3,
            version: 'SIMPLE',
            mapSize: 1000,
            maxZoom: 5,
            minZoom: 2,
            pitchEnabled: false,
            rotateEnabled: false
        })
    });
    scene.setBgColor('rgb(94, 182, 140)');

    const imagelayer = new ImageLayer({}).source(
        '/bg.jpg',
        {
            parser: {
                type: 'image',
                extent: [360, 400, 640, 600]
            }
        }
    );

    imagelayer.on('click', e => {
        // console.log(e);
        let { lng, lat } = e.lngLat
        popup = new Popup({
            offsets: [0, 0],
            closeButton: false
        })
            .setLnglat(e.lngLat)
            .setHTML(`<button class="el-button el-button--primary" onClick="add_popup(${lng},${lat})">选择位置</button>`);
        scene.addPopup(popup);
    });

    scene.on('loaded', () => {
        // fetch('https://gw.alipayobjects.com/os/bmw-prod/7dc0d454-fabc-4461-a5d5-d404dadb49a9.json')
        //     .then(res => res.json())
        //     .then(data => {
        //         const textlayer = new PointLayer({ zIndex: 2 })
        //             .source(
        //                 data,
        //                 {
        //                     parser: {
        //                         type: 'json',
        //                         x: 'x',
        //                         y: 'y'
        //                     }
        //                 }
        //             )
        //             .shape('t', 'text')
        //             .size(12)
        //             .active({
        //                 color: '#00f',
        //                 mix: 0.9
        //             })
        //             .color('rgb(86, 156, 214)')
        //             .style({
        //                 textAnchor: 'center', // 文本相对锚点的位置 center|left|right|top|bottom|top-left
        //                 spacing: 2, // 字符间距
        //                 fontWeight: '800',
        //                 padding: [1, 1], // 文本包围盒 padding [水平，垂直]，影响碰撞检测结果，避免相邻文本靠的太近
        //                 stroke: '#ffffff', // 描边颜色
        //                 strokeWidth: 2, // 描边宽度
        //                 textAllowOverlap: true
        //             });
        //         scene.addLayer(textlayer);
        //     });

        scene.addLayer(imagelayer);

    });

})

window.add_popup = (x, y) => {
    // console.log(x,y);
    dialogVisible.value = true

    popup.close()

    addForm.x = 500 + 2.8 * x
    addForm.y = 500 + 2.8 * y
    // const textlayer = new PointLayer({ zIndex: 2 })
    //     .source(
    //         [
    //             {
    //                 "x": 500 + 2.8 * x,
    //                 "y": 500 + 2.8 * y,
    //                 "t": "小屋"
    //             }
    //         ],
    //         {
    //             parser: {
    //                 type: 'json',
    //                 x: 'x',
    //                 y: 'y'
    //             }
    //         }
    //     )
    //     .shape('t', 'text')
    //     .size(12)
    //     .active({
    //         color: '#00f',
    //         mix: 0.9
    //     })
    //     .color('rgb(86, 156, 214)')
    //     .style({
    //         textAnchor: 'center', // 文本相对锚点的位置 center|left|right|top|bottom|top-left
    //         spacing: 2, // 字符间距
    //         fontWeight: '800',
    //         padding: [1, 1], // 文本包围盒 padding [水平，垂直]，影响碰撞检测结果，避免相邻文本靠的太近
    //         stroke: '#ffffff', // 描边颜色
    //         strokeWidth: 2, // 描边宽度
    //         textAllowOverlap: true
    //     });
    // scene.addLayer(textlayer);
}


const dialogVisible = ref(false)
const addFormRef = ref()
const addForm = reactive({
    title: '',
    capacity: '',
    lab_type: '',
    college_type: '',
    x: 0,
    y: 0
})

const rules = ref({
    title: [
        { required: true, message: '请输入实验室名称', trigger: 'blur' },
        { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
    ],
    capacity: [
        { required: true, message: '请输入容纳人数', trigger: 'blur' },
        // { type: 'number', message: '容纳人数必须为数字值' }
    ],
    lab_type: [
        { required: true, message: '请选择实验室类型', trigger: 'change' }
    ],
    college_type: [
        { required: true, message: '请选择所属学院', trigger: 'change' }
    ]
})

const handleAddConfirm = () => {
    {
    addFormRef.value.validate((valid) => {
        if (valid) {
            dialogVisible.value = false

            addTextLayer()
        }
    })
}

const textlayer = new PointLayer({ zIndex: 2 })
    .source(
        [
            {
                "x": addForm.x,
                "y": addForm.y,
                "t": addForm.title
            }
        ],
        {
            parser: {
                type: 'json',
                x: 'x',
                y: 'y'
            }
        }
    )
    .shape('t', 'text')
    .size(14)
    .active({
        color: '#00f',
        mix: 0.9
    })
    .color('rgb(86, 156, 214)')
    .style({
        textAnchor: 'center', // 文本相对锚点的位置 center|left|right|top|bottom|top-left
        spacing: 2, // 字符间距
        fontWeight: '800',
        padding: [1, 1], // 文本包围盒 padding [水平，垂直]，影响碰撞检测结果，避免相邻文本靠的太近
        stroke: '#ffffff', // 描边颜色
        strokeWidth: 2, // 描边宽度
        textAllowOverlap: true
    });
scene.addLayer(textlayer);
}

</script>