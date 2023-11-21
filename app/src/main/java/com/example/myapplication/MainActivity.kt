package com.example.myapplication

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.assets.RenderableSource
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode

private fun Any.build() {
    TODO("Not yet implemented")
}

class MainActivity : AppCompatActivity() {

    private fun setRecenterMode(root: RenderableSource.RecenterMode): Any {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var renderableSource = RenderableSource.builder()
            .setSource(
                this,
                Uri.parse("3DModel-1.glb"),
                RenderableSource.SourceType.GLB
            )
            .setRecenterMode(RenderableSource.RecenterMode.ROOT)
            .build()

        ModelRenderable.builder()
            .setSource(
                this,
                renderableSource // This is the model we built earlier
            )
            .build()
            .thenAccept { modelRenderable ->
                var renderable = modelRenderable // Store in a variable to reference later
                var fragment =
                    supportFragmentManager.findFragmentById(R.id.ux_fragment)
                            as ArFragment

                fragment.setOnTapArPlaneListener { hitResult, _, _ ->
                    val anchorNode = AnchorNode(hitResult.createAnchor())
                    anchorNode.setParent(fragment.arSceneView.scene)
                    val transformableNode = TransformableNode(fragment.transformationSystem)
                    transformableNode.renderable = renderable // This is the built ModelRenderable
                    transformableNode.setParent(anchorNode)
                    transformableNode.select()
                }
            }
        }
    }




