form-zip returns a zipper from a clojure form.

    user=> (require '[clojure.zip :as zip])
    user=> (use 'form-zip.core)
    user=> (-> '{1 2 3 4} form-zip  zip/next zip/remove zip/root)
    {3 4}

fz-node-seq returns a seq of the nodes.

    user=> (fz-node-seq '{1 2 3 4})
    ({1 2, 3 4} [1 2] 1 2 [3 4] 3 4)

a one hour hack, probably full of edge cases i haven't thought of.

