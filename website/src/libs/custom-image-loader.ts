// custom-image-loader.js
// Code: https://stackoverflow.com/a/78220610

import nextConfig from "../../next.config";
import path from "path";

const basePath = nextConfig.basePath;

export default function myImageLoader({
  src,
  width,
}: {
  src: string;
  width: string;
}) {
  if (basePath && path.isAbsolute(src)) {
    return `${basePath}${src}?width=${width}`;
  }
  return `${src}?width=${width}`;
}
