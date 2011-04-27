{
	"patcher" : 	{
		"fileversion" : 1,
		"rect" : [ 1061.0, 458.0, 824.0, 777.0 ],
		"bglocked" : 0,
		"defrect" : [ 1061.0, 458.0, 824.0, 777.0 ],
		"openrect" : [ 0.0, 0.0, 0.0, 0.0 ],
		"openinpresentation" : 0,
		"default_fontsize" : 12.0,
		"default_fontface" : 0,
		"default_fontname" : "Arial",
		"gridonopen" : 0,
		"gridsize" : [ 15.0, 15.0 ],
		"gridsnaponopen" : 0,
		"toolbarvisible" : 1,
		"boxanimatetime" : 200,
		"imprint" : 0,
		"enablehscroll" : 1,
		"enablevscroll" : 1,
		"devicewidth" : 0.0,
		"boxes" : [ 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "Since we don't want to poll. We setup observers on the clip slots to assemble the bitmask when they change.",
					"linecount" : 4,
					"numinlets" : 1,
					"patching_rect" : [ 445.0, 493.0, 160.0, 62.0 ],
					"numoutlets" : 0,
					"fontname" : "Arial",
					"id" : "obj-17",
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "- #1",
					"numinlets" : 2,
					"patching_rect" : [ 208.0, 488.0, 32.5, 20.0 ],
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-8",
					"outlettype" : [ "int" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "js loaded_clips_bitmask.js",
					"numinlets" : 2,
					"patching_rect" : [ 475.0, 622.0, 149.0, 20.0 ],
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-6",
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "69",
					"numinlets" : 2,
					"patching_rect" : [ 464.0, 661.0, 50.0, 18.0 ],
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-4",
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "outlet",
					"numinlets" : 1,
					"patching_rect" : [ 471.0, 691.0, 25.0, 25.0 ],
					"numoutlets" : 0,
					"id" : "obj-5",
					"comment" : ""
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "20",
					"numinlets" : 2,
					"patching_rect" : [ 203.0, 466.0, 44.0, 18.0 ],
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-54",
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "+ 1",
					"numinlets" : 2,
					"patching_rect" : [ 254.0, 464.0, 33.0, 20.0 ],
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-126",
					"outlettype" : [ "int" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "observe_clip_slots_fire_on_change",
					"numinlets" : 2,
					"patching_rect" : [ 447.0, 568.0, 199.0, 20.0 ],
					"numoutlets" : 3,
					"fontname" : "Arial",
					"id" : "obj-12",
					"outlettype" : [ "bang", "", "bang" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "t b",
					"numinlets" : 1,
					"patching_rect" : [ 227.0, 532.0, 24.0, 20.0 ],
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-136",
					"outlettype" : [ "bang" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "gate",
					"numinlets" : 2,
					"patching_rect" : [ 241.0, 509.0, 34.0, 20.0 ],
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-127",
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "id 110",
					"numinlets" : 2,
					"patching_rect" : [ 335.0, 559.0, 50.0, 18.0 ],
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-121",
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "t b",
					"numinlets" : 1,
					"patching_rect" : [ 82.0, 76.0, 24.0, 20.0 ],
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-35",
					"outlettype" : [ "bang" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "t l b",
					"numinlets" : 1,
					"patching_rect" : [ 167.0, 359.0, 32.5, 20.0 ],
					"numoutlets" : 2,
					"fontname" : "Arial",
					"id" : "obj-36",
					"outlettype" : [ "", "bang" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : ">= #1",
					"numinlets" : 2,
					"patching_rect" : [ 209.0, 435.0, 41.0, 20.0 ],
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-37",
					"outlettype" : [ "int" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "gate",
					"numinlets" : 2,
					"patching_rect" : [ 166.0, 483.0, 34.0, 20.0 ],
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-40",
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "+ 1",
					"numinlets" : 2,
					"patching_rect" : [ 209.0, 402.0, 32.5, 20.0 ],
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-41",
					"outlettype" : [ "int" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "19",
					"numinlets" : 2,
					"patching_rect" : [ 209.0, 377.0, 32.5, 18.0 ],
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-42",
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "-1",
					"numinlets" : 2,
					"patching_rect" : [ 261.0, 338.0, 32.5, 18.0 ],
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-43",
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "Unfortunately we have to poll Live.",
					"linecount" : 2,
					"numinlets" : 1,
					"patching_rect" : [ 269.0, 89.0, 150.0, 34.0 ],
					"numoutlets" : 0,
					"fontname" : "Arial",
					"id" : "obj-44",
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "Takes an Live ID for a Track and returns a bitmask that represents all rows in the track that have clips",
					"linecount" : 5,
					"numinlets" : 1,
					"patching_rect" : [ 283.0, 144.0, 155.0, 75.0 ],
					"numoutlets" : 0,
					"fontname" : "Arial",
					"id" : "obj-46",
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "t b",
					"numinlets" : 1,
					"patching_rect" : [ 29.0, 267.0, 24.0, 20.0 ],
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-47",
					"outlettype" : [ "bang" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "69",
					"numinlets" : 2,
					"patching_rect" : [ 76.0, 603.0, 50.0, 18.0 ],
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-49",
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "outlet",
					"numinlets" : 1,
					"patching_rect" : [ 76.0, 643.0, 25.0, 25.0 ],
					"numoutlets" : 0,
					"id" : "obj-50",
					"comment" : ""
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "t b",
					"numinlets" : 1,
					"patching_rect" : [ 296.0, 278.0, 24.0, 20.0 ],
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-51",
					"outlettype" : [ "bang" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "js loaded_clips_bitmask.js",
					"numinlets" : 2,
					"patching_rect" : [ 166.0, 560.0, 149.0, 20.0 ],
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-52",
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "t b l",
					"numinlets" : 1,
					"patching_rect" : [ 167.0, 263.0, 32.5, 18.0 ],
					"numoutlets" : 2,
					"fontname" : "Arial Bold",
					"id" : "obj-55",
					"outlettype" : [ "bang", "" ],
					"fontsize" : 10.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "get has_clip",
					"numinlets" : 2,
					"patching_rect" : [ 80.0, 280.0, 75.0, 18.0 ],
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-56",
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "live.object",
					"numinlets" : 2,
					"patching_rect" : [ 166.0, 301.0, 64.0, 20.0 ],
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-57",
					"outlettype" : [ "" ],
					"fontsize" : 12.0,
					"saved_object_attributes" : 					{
						"_persistence" : 0
					}

				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "route clip_slots",
					"numinlets" : 1,
					"patching_rect" : [ 167.0, 209.0, 84.0, 18.0 ],
					"numoutlets" : 2,
					"fontname" : "Arial Bold",
					"id" : "obj-58",
					"outlettype" : [ "", "" ],
					"fontsize" : 10.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "t b l",
					"numinlets" : 1,
					"patching_rect" : [ 186.0, 95.0, 32.5, 18.0 ],
					"numoutlets" : 2,
					"fontname" : "Arial Bold",
					"id" : "obj-59",
					"outlettype" : [ "bang", "" ],
					"fontsize" : 10.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "zl group 2",
					"numinlets" : 2,
					"patching_rect" : [ 167.0, 234.0, 58.0, 18.0 ],
					"numoutlets" : 2,
					"fontname" : "Arial Bold",
					"id" : "obj-60",
					"outlettype" : [ "", "" ],
					"fontsize" : 10.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "route has_clip",
					"numinlets" : 1,
					"patching_rect" : [ 166.0, 337.0, 78.0, 18.0 ],
					"numoutlets" : 2,
					"fontname" : "Arial Bold",
					"id" : "obj-64",
					"outlettype" : [ "", "" ],
					"fontsize" : 10.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "get clip_slots",
					"numinlets" : 2,
					"patching_rect" : [ 102.0, 152.0, 80.0, 18.0 ],
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-65",
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "live.object",
					"numinlets" : 2,
					"patching_rect" : [ 167.0, 179.0, 64.0, 20.0 ],
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-66",
					"outlettype" : [ "" ],
					"fontsize" : 12.0,
					"saved_object_attributes" : 					{
						"_persistence" : 0
					}

				}

			}
, 			{
				"box" : 				{
					"maxclass" : "inlet",
					"numinlets" : 0,
					"patching_rect" : [ 82.0, 16.0, 25.0, 25.0 ],
					"numoutlets" : 1,
					"id" : "obj-28",
					"outlettype" : [ "" ],
					"comment" : ""
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "inlet",
					"numinlets" : 0,
					"patching_rect" : [ 189.0, 15.0, 25.0, 25.0 ],
					"numoutlets" : 1,
					"id" : "obj-1",
					"outlettype" : [ "" ],
					"comment" : ""
				}

			}
 ],
		"lines" : [ 			{
				"patchline" : 				{
					"source" : [ "obj-12", 0 ],
					"destination" : [ "obj-4", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-12", 1 ],
					"destination" : [ "obj-6", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-12", 2 ],
					"destination" : [ "obj-6", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-127", 0 ],
					"destination" : [ "obj-12", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-121", 0 ],
					"destination" : [ "obj-12", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-35", 0 ],
					"destination" : [ "obj-65", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-59", 0 ],
					"destination" : [ "obj-65", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-59", 1 ],
					"destination" : [ "obj-66", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-1", 0 ],
					"destination" : [ "obj-59", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-4", 0 ],
					"destination" : [ "obj-5", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-41", 0 ],
					"destination" : [ "obj-37", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-37", 0 ],
					"destination" : [ "obj-40", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-37", 0 ],
					"destination" : [ "obj-127", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-28", 0 ],
					"destination" : [ "obj-35", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-56", 0 ],
					"destination" : [ "obj-57", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-52", 0 ],
					"destination" : [ "obj-49", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-58", 0 ],
					"destination" : [ "obj-60", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-57", 0 ],
					"destination" : [ "obj-64", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-55", 1 ],
					"destination" : [ "obj-57", 1 ],
					"hidden" : 0,
					"midpoints" : [ 190.0, 283.0, 220.5, 283.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-55", 0 ],
					"destination" : [ "obj-56", 0 ],
					"hidden" : 0,
					"midpoints" : [ 176.5, 283.0, 156.0, 283.0, 156.0, 268.0, 89.5, 268.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-66", 0 ],
					"destination" : [ "obj-58", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-65", 0 ],
					"destination" : [ "obj-66", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-60", 0 ],
					"destination" : [ "obj-55", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-51", 0 ],
					"destination" : [ "obj-52", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-58", 0 ],
					"destination" : [ "obj-51", 0 ],
					"hidden" : 0,
					"midpoints" : [ 176.5, 229.0, 237.0, 229.0, 237.0, 265.0, 305.5, 265.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-49", 0 ],
					"destination" : [ "obj-50", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-58", 0 ],
					"destination" : [ "obj-47", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-47", 0 ],
					"destination" : [ "obj-49", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-51", 0 ],
					"destination" : [ "obj-43", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-43", 0 ],
					"destination" : [ "obj-42", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-42", 0 ],
					"destination" : [ "obj-41", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-41", 0 ],
					"destination" : [ "obj-42", 1 ],
					"hidden" : 0,
					"midpoints" : [ 218.5, 425.0, 249.0, 425.0, 249.0, 371.0, 232.0, 371.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-40", 0 ],
					"destination" : [ "obj-52", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-64", 0 ],
					"destination" : [ "obj-36", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-36", 1 ],
					"destination" : [ "obj-42", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-36", 0 ],
					"destination" : [ "obj-40", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-55", 1 ],
					"destination" : [ "obj-121", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-127", 0 ],
					"destination" : [ "obj-136", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-136", 0 ],
					"destination" : [ "obj-121", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-126", 0 ],
					"destination" : [ "obj-54", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-41", 0 ],
					"destination" : [ "obj-126", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-37", 0 ],
					"destination" : [ "obj-54", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-6", 0 ],
					"destination" : [ "obj-4", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-54", 0 ],
					"destination" : [ "obj-8", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-8", 0 ],
					"destination" : [ "obj-127", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
 ]
	}

}
