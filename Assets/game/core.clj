(ns game.core
  (:use arcadia.core arcadia.linear)
  (:import [UnityEngine Collider2D Physics
            GameObject Input Rigidbody2D Rigidbody
            Vector2 Mathf Resources Transform
            Collision2D Physics2D]
           ArcadiaState)
  (:require [game.movement :as m]))

  (defn setup []
    (let [player  (object-named "unitychan")]
    ;  (reset! fighter-atom fighter)
      (roles+ player m/player-roles)
      )) ; NEW
