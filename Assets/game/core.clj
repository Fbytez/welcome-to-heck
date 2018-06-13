(ns game.core
  (:use arcadia.core arcadia.linear)
  (:import [UnityEngine Collider2D Physics
            GameObject Input Rigidbody2D Rigidbody
            Vector2 Mathf Resources Transform
            Collision2D Physics2D]
           ArcadiaState))

(defn game-start []
  (log "Heck day")
  )

  (defn bearing-vector [angle]
    (let [angle (* Mathf/Deg2Rad angle)]
      (v2 (Mathf/Cos angle) (Mathf/Sin angle))))

  (defn abs-angle [v]
    (* Mathf/Rad2Deg
       (Mathf/Atan2 (.y v) (.x v))))

  (defn controller-vector []
   (v2 (Input/GetAxis "Horizontal")
       (Input/GetAxis "Vertical")))

  (defn wasd-key []
    (or (Input/GetKey "w")
        (Input/GetKey "a")
        (Input/GetKey "s")
        (Input/GetKey "d")))

  (defn player-movement-fixed-update [obj k] ; We'll only use the `obj` parameter
    (with-cmpt obj [rb Rigidbody]          ; Gets the Rigidbody2D component
      (when (wasd-key)                       ; Checks for WASD key
        (.MoveRotation rb (abs-angle (controller-vector))) ; Rotates towards key
        (set! (.angularVelocity rb) 0)
        (.AddForce rb                                      ; Moves forwards
          (v2* (bearing-vector (.rotation rb))
               3)))))

   (def player-roles
     {::movement {:fixed-update #'player-movement-fixed-update}})

  (defn setup []
    (let [player  (object-named "unitychan")]
    ;  (reset! fighter-atom fighter)
      (roles+ player player-roles)
      )) ; NEW
